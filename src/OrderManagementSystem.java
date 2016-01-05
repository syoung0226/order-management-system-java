import command.order.CsvOrderCommand;
import command.order.OrderCommand;
import data.Order;
import file.FileService;
import file.csv.CsvService;
import file.csv.data.CsvData;
import queue.Queue;
import queue.impl.CircularArrayQueue;
import ui.UIOrderCommand;

public class OrderManagementSystem {

	public static void orderManagementSystem() {
		FileService<CsvData> csvService = new CsvService("order.csv");
		Queue<Order> orderQueue = new CircularArrayQueue<>(10);
		OrderCommand orderCommand = new CsvOrderCommand(csvService, orderQueue);
		UIOrderCommand uiOrderCommand = new UIOrderCommand(orderCommand);
		uiOrderCommand.start();
	}
}
