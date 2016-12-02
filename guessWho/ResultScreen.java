import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ResultScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ResultScreen extends World
{
    
    GameSession gameSession;
    GifImage winImg = new GifImage("Congrats-You-Win.gif");
    GifImage loseImg = new GifImage("you-lose.gif");
    GifImage drawImg = new GifImage("draw.gif");
    GifImage disImg = new GifImage("disconnect.gif");
    
    DummyImage wi = new DummyImage(winImg, false);
    DummyImage lo = new DummyImage(loseImg, false);
    DummyImage draw = new DummyImage(drawImg, false);
    DummyImage dis = new DummyImage(disImg, false);
    boolean isShowIntro = false, isFirst = false;
    //GifImage gImg = new GifImage("Introduction.gif");
    /**
     * Constructor for objects of class ResultScreen.
     * 
     */
    public ResultScreen(GameSession gameSession)
    {    
        super(1536, 864, 1); 
        this.gameSession = gameSession;
        prepare();
    }
    
    private void prepare()
    {
        Character yourChar = gameSession.getYou().getChosenChar();
        yourChar.resizeOnScale(2);
        addObject(yourChar,getWidth()/2,getHeight()/2);
    }
    
    public void act(){
        if(gameSession.getMe().isFinished() && gameSession.getYou().isFinished()){
            drawResult();//Draw Screen
        }else if(gameSession.getMe().isFinished()){
            winResult();//Me wins
        }else if(gameSession.getYou().isFinished()){
            loseResult();//Me lose        
        }else if(!gameSession.getMe().isFinished() && !gameSession.getYou().isFinished()){
            disconnectedResult();
        }
    }
    public void drawResult(){
        draw = new DummyImage(drawImg, isFirst);
        
        //if(isShowIntro){
            if(isFirst){
                isFirst = false;
            }
            addObject(draw, 761, 545);
        //}
        
        //addObject(di, 761, 545);
        System.out.println("The game resulted in a draw!");
    }
    
    public void winResult(){
        wi = new DummyImage(winImg, isFirst);
        
        //if(isShowIntro){
            if(isFirst){
                isFirst = false;
            }
            addObject(wi, 761, 545);
        //}
        
        //addObject(di, 761, 545);
        System.out.println("You win");
    }
    
    public void loseResult(){
        lo = new DummyImage(loseImg, isFirst);
        
        //if(isShowIntro){
            if(isFirst){
                isFirst = false;
            }
            addObject(lo, 761, 545);
        //}
        
        //addObject(di, 761, 545);
        System.out.println("You Lose");
    }
    
    public void disconnectedResult(){
        dis = new DummyImage(disImg, isFirst);
        
        //if(isShowIntro){
            if(isFirst){
                isFirst = false;
            }
            addObject(dis, 761, 545);
        //}
        
        //addObject(di, 761, 545);
        System.out.println("Player was disconnected!!");
    
    }
    
    public void addedToWorld(World world)
    {
        
        
    }
}
