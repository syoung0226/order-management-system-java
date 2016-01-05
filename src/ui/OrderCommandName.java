package ui;

public enum OrderCommandName {

	ORDER(1),
	PROCESS(2),
	FIND(3),
	LIST(4),
	LOG(5),
	LOGISTICS(6),
	QUIT(7),
	ERROR(8);

	private final int value;

	OrderCommandName(int value) {
		this.value = value;
	}

	public static OrderCommandName toEnum(int i) {
		switch (i) {
			case 1:
				return ORDER;
			case 2:
				return PROCESS;
			case 3:
				return FIND;
			case 4:
				return LIST;
			case 5:
				return LOG;
			case 6:
				return LOGISTICS;
			case 7:
				return QUIT;
			default:
				return ERROR;
		}
	}

	public int getValue() {
		return value;
	}
}
