package command;

public enum CommandsEnum {
	LOGIN {
		@Override
		public Command createCommand() {
			return new LoginCommand();
		}
	},
	LOGOUT {
		@Override
		public Command createCommand() {
			return new LogoutCommand();
		}
	},
	PAYMENT {
		@Override
		public Command createCommand() {
			return new PaymentCommand();
		}
	},
	USER {
		@Override
		public Command createCommand() {
			return new UserCommand();
		}
	},
	MAKE_PAYMENT {
		@Override
		public Command createCommand() {
			return new MakePaymentCommand();
		}
	};
	public abstract Command createCommand();
}