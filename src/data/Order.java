package data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class Order {

	private Integer orderId;
	private Integer productId;
	private String userId;
	private Date registerDate;
	private LogisticStatus logisticStatus;

	public Order(Integer orderId, Integer productId, String userId) {
		this.orderId = orderId;
		this.productId = productId;
		this.userId = userId;
		registerDate = new Date();
		logisticStatus = LogisticStatus.F;
	}
}
