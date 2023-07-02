package VeggiApp.Exception;

public class FeedBackException  extends RuntimeException{

    FeedBackException(){

    }
    public FeedBackException(String msg){
        super(msg);
    }
}
