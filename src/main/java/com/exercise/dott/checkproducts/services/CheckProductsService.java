package com.exercise.dott.checkproducts.services;

import com.exercise.dott.checkproducts.dto.CustomInterval;
import com.exercise.dott.checkproducts.exception.NotFoundException;
import com.exercise.dott.checkproducts.models.OrderPlacedInterval;
import com.exercise.dott.checkproducts.repository.OrderRepository;
import com.exercise.dott.checkproducts.utils.Constants;
import com.exercise.dott.checkproducts.utils.DateUtils;
import com.exercise.dott.checkproducts.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CheckProductsService {

    private static final int INDEX_INITIAL_DATE = 0;
    private static final int INDEX_FINAL_DATE = 1;
    private static final int INDEX_CUSTOM_INTERVALS = 2;

    @Autowired
    OrderRepository orderRepository;

    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATETIME_FORMAT);
    public List<OrderPlacedInterval> orderPlacedInterval;

    /**
     * Check orders placed during an interval
     * @param args the interval to be used when searching for orders.
     *  You can provide two to three arguments.
     *  The first and second argument are the initial date and final date in the format yyyy-MM-dd HH:mm:ss, while
     *  the third argument is optional and represent a list of custom intervals in the format: "1-3,4-5,6-12,>12"
     */
    public void checkOrders(String ...args) {
        if (args == null || args.length < 2 || args.length > 3 ) throw new IllegalArgumentException("Invalid arguments");
        validateInterval(args[INDEX_INITIAL_DATE], args[INDEX_FINAL_DATE]);
        if (args.length == 3 && !args[INDEX_CUSTOM_INTERVALS].isEmpty()) {
            validateCustomIntervals(args[INDEX_CUSTOM_INTERVALS]);
            checkPlacedOrders(args[INDEX_INITIAL_DATE], args[INDEX_FINAL_DATE], args[INDEX_CUSTOM_INTERVALS]);
        } else {
            checkPlacedOrders(args[INDEX_INITIAL_DATE], args[INDEX_FINAL_DATE], null);
        }
        writeResult();
    }

    /**
     * Search in the database for order placed in the passed interval.
     * @param initialDate the initial date in the format: yyyy-MM-dd HH:mm:ss
     * @param finalDate the final date in the format: yyyy-MM-dd HH:mm:ss
     * @param intervals the custom intervals in the format: "1-3,4-5,6-12,>12"
     * @return the order count by interval
     */
    private List<OrderPlacedInterval> checkPlacedOrders(String initialDate, String finalDate, String intervals) {
        if (intervals == null) {
            List<Object[]> orders = orderRepository.findOrdersPlacedInTheRange(initialDate, finalDate);
            orderPlacedInterval = OrderPlacedInterval.converter(orders);
        } else {
            orderPlacedInterval = orderRepository.findByCustomInterval(createCustomIntervals(intervals), initialDate, finalDate);
        }
        if (orderPlacedInterval == null || orderPlacedInterval.isEmpty()) throw new NotFoundException("No orders were found in the range.");
        return orderPlacedInterval;
    }

    /**
     * Validates the input data
     * @param initialDate the initial date
     * @param finalDate the final date
     */
    private void validateInterval(String initialDate, String finalDate) {
        if (initialDate == null || initialDate.isEmpty()) throw new IllegalArgumentException();
        if (finalDate == null || finalDate.isEmpty()) throw new IllegalArgumentException();

        try {
            LocalDateTime iDate = DateUtils.toLocalDateTime(sdf.parse(initialDate));
            LocalDateTime fDate = DateUtils.toLocalDateTime(sdf.parse(finalDate));
            if (iDate.isAfter(fDate)) throw new IllegalArgumentException();
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Validates the custom interval data
     * @param customIntervals the custom interval data
     */
    private void validateCustomIntervals(String customIntervals) {
        Pattern pattern = Pattern.compile("^(\\d+-\\d+(?:,\\d+-\\d+)*)?(?:,>\\d+)*");
        if (!pattern.matcher(customIntervals).matches()) throw new IllegalArgumentException();
    }

    /**
     * Writes in the result.log file and in the console the result of the orders search
     */
    private void writeResult() {
        FileUtils.cleanLogFile(Constants.RESULT_FILE);
        File file = new File(Constants.RESULT_FILE);
        FileUtils.write(file, "Result:");
        System.out.println("Result");
        StringBuffer stringBuffer = new StringBuffer();
        for (OrderPlacedInterval o : orderPlacedInterval) {
            stringBuffer.append(o.getRanges() + ": " + o.getCount() + "\n");
        }
        System.out.println(stringBuffer.toString());
        FileUtils.write(file, stringBuffer.toString());
    }

    /**
     * Creates the CustomInterval POJO for the interval data passed
     * @param intervals the string containing the intervals
     * @return the list of CustomInterval
     */
    private List<CustomInterval> createCustomIntervals(String intervals) {
        String[] ranges = intervals.split(",");
        List<CustomInterval> customIntervals = Arrays.stream(ranges).map(desc -> {
          String[] intInterval = desc.split("-");
          if (desc.contains(">")) {
              return new CustomInterval(null, null, desc, true);
          } else {
              return new CustomInterval(Integer.valueOf(intInterval[0]), Integer.valueOf(intInterval[1]), desc + " months", false);

          }
        }).collect(Collectors.toList());

        return customIntervals;
    }
}
