package vip.itellyou.util.exception;
/**
 * 自定义异常类
 * 记录因为用户操作不当而违反业务逻辑的异常
 * @author 程浩
 *
 */
public class RuleException extends RuntimeException {
	//自定义异常类记录好异常的信息
	public RuleException(String message){
		super(message);
	}
}
