package service;

public class UserService {
	private CardService cardService;

	private AddressService addressService;

	@Override
	public String toString() {
		return "UserService{" +
				"cardService=" + cardService +
				", addressService=" + addressService +
				'}';
	}
}
