package Ecom.Exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyErrorClass {

	private String message;
	private LocalDateTime localDateTimes;
	private String desc;
}
