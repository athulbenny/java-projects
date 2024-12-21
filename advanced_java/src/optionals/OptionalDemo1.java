package optionals;

import java.util.Optional;

public class OptionalDemo1 {

	public static void main(String[] args) {
		Optional<String> nonEmptyOptional = Optional.of("hello");
		Optional<String> nullableOptional = Optional.ofNullable(null);
		Optional<String> emptyOptional = Optional.empty();
		
		if(nonEmptyOptional.isPresent()) {
			System.out.println(nonEmptyOptional.get());
		}
		
		System.out.println(nullableOptional.orElse("hi"));

		Optional<String> result = nonEmptyOptional
				.filter(item->item.length()>3)
				.map(String::toUpperCase);
		System.out.println(result);
		
		String valueOrError = nullableOptional.orElseThrow(() -> new RuntimeException("Value not present"));
		System.out.println(valueOrError);
	}

}
