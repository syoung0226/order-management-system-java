package command.order;

import data.Order;

import java.util.List;

public interface OrderCommand {

	void order(Order order);

	void process();

	Order find(Integer orderId);

	List<Order> list();

	List<Order> logisticsProcessedList();

	void logistic(Integer orderId);
}
