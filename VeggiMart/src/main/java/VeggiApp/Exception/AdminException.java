package VeggiApp.Exception;



public class AdminException  extends  RuntimeException{
    public AdminException(){}
    public AdminException(String mesg) {
        super(mesg);
    }
}
