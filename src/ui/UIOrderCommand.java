package ui;

import command.order.OrderCommand;
import data.Order;
import utils.ValidUtils;

import java.util.List;
import java.util.Scanner;

public class UIOrderCommand {

	private final OrderCommand command;
	private final Scanner scanner = new Scanner(System.in);

	public UIOrderCommand(OrderCommand command) {
		this.command = command;
	}

	public void start() {

		OrderCommandName orderCommandName;

		while (true) {

			printMenu();
			orderCommandName = OrderCommandName.toEnum(scanner.nextInt());
			switch (orderCommandName) {
				case ORDER:
					command.order(getOrder());
					break;
				case PROCESS:
					command.process();
					break;
				case FIND:
					printOrder(command.find(getOrderId()));
					break;
				case LIST:
					printOrderList(command.list());
					break;
				case LOG:
					printOrderList(command.logisticsProcessedList());
					break;
				case LOGISTICS:
					command.logistic(getOrderId());
					break;
				case QUIT:
					return;
				case ERROR:
				default:
					break;
			}

			System.out.println("");
		}
	}

	private void printOrder(Order order) {
		System.out.println(order);
	}

	private void printOrderList(List<Order> orderList) {

		if (ValidUtils.isEmptyList(orderList)) {
			return;
		}
		for (Order order : orderList) {
			printOrder(order);
		}
	}

	private void printMenu() {
		System.out.println("=====order management system=====");
		System.out.println("1. order");
		System.out.println("2. process");
		System.out.println("3. find");
		System.out.println("4. list");
		System.out.println("5. logistic processed list");
		System.out.println("6. logistic");
		System.out.println("7. Program Quit");
		System.out.print(">> number : ");
	}

	private Order getOrder() {
		int orderId = getOrderId();
		System.out.print(">> productId : ");
		int productId = scanner.nextInt();
		System.out.print(">> userId : ");
		String userId = scanner.next();
		return new Order(orderId, productId, userId);
	}

	private Integer getOrderId() {
		System.out.print(">> orderId : ");
		return scanner.nextInt();
	}
}

