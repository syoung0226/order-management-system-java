package command.order;

import data.LogisticStatus;
import data.Order;
import file.FileService;
import file.FileWriteMode;
import file.csv.data.CsvData;
import lombok.AllArgsConstructor;
import queue.Queue;
import utils.ConvertUtils;
import utils.Converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class CsvOrderCommand implements OrderCommand {

	private FileService<CsvData> csvService;
	private Queue<Order> orderQueue;

	@Override public void order(Order order) {
		orderQueue.enqueue(order);
	}

	@Override public void process() {
		List<Order> orderList = orderQueue.dequeueAll();
		List<CsvData> csvDataList = toCsvList(orderList);
		csvService.write(csvDataList, FileWriteMode.APPEND);
	}

	private List<CsvData> toCsvList(List<Order> orderList) {
		List<CsvData> result = new ArrayList<>();
		for (Order order : orderList) {
			result.add(ConvertUtils.convert(order, new Converter<Order, CsvData>() {
				@Override public CsvData convert(Order source) {
					return getCsvData(source);
				}
			}));
		}
		return result;
	}

	private CsvData getCsvData(Order source) {
		CsvData result = new CsvData();
		List<String> dataList = new ArrayList<>();
		dataList.add(source.getOrderId().toString());
		dataList.add(source.getProductId().toString());
		dataList.add(source.getUserId());
		dataList.add(String.valueOf(source.getRegisterDate().getTime()));
		dataList.add(source.getLogisticStatus().name());
		result.setDataList(dataList);
		return result;
	}

	@Override public List<Order> list() {
		return ConvertUtils.convertList(csvService.read(), new Converter<CsvData, Order>() {
			@Override public Order convert(CsvData source) {
				return getOrder(source);
			}
		});
	}

	private Order getOrder(CsvData source) {
		Order result = new Order();
		List<String> dataList = source.getDataList();
		int index = 0;

		result.setOrderId(Integer.parseInt(dataList.get(index++)));
		result.setProductId(Integer.parseInt(dataList.get(index++)));
		result.setUserId(dataList.get(index++));
		result.setRegisterDate(new Date(Long.parseLong(dataList.get(index++))));
		result.setLogisticStatus(LogisticStatus.valueOf(dataList.get(index)));
		return result;
	}

	@Override public Order find(Integer orderId) {
		for (Order order : list()) {
			if (order.getOrderId().equals(orderId)) {
				return order;
			}
		}
		return null;
	}

	@Override public List<Order> logisticsProcessedList() {
		List<Order> result = new ArrayList<>();
		for (Order order : list()) {
			if (order.getLogisticStatus().equals(LogisticStatus.T)) {
				result.add(order);
			}
		}
		return result;
	}

	@Override public void logistic(Integer orderId) {
		List<Order> modifyOrderList = modifyOrderList(orderId, LogisticStatus.T);
		csvService.write(toCsvList(modifyOrderList), FileWriteMode.OVERWRITE);
	}

	private List<Order> modifyOrderList(Integer orderId, LogisticStatus t) {
		List<Order> result = list();
		for (Order order : result) {
			if (order.getOrderId().equals(orderId)) {
				order.setLogisticStatus(t);
			}
		}
		return result;
	}
}
