package comp1110.ass2.gui;
import javafx.scene.text.Text;

import comp1110.ass2.AI;
import comp1110.ass2.Board;
import comp1110.ass2.RailroadInk;
import comp1110.ass2.Tile;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

import static javafx.scene.paint.Color.LIGHTGREY;


/**
 * A very simple viewer for tile placements in the Railroad Ink game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various tile placements.
 */
public class Viewer extends Application {

    // Authorship: Isaac Wang for most of the JavaFX GUI function
    //Authorship: Xueting Ren for some of the JavaFX function

    public static void main(String[] args) {
        launch(args);
    }

    /* board layout */
    private static final int VIEWER_WIDTH = 1024;
    private static final int VIEWER_HEIGHT = 768;
    private static final String URI_BASE = "assets/";
    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group allplacements = new Group();
    private final Group labels = new Group();
    private Group specialButton = new Group();
    private Group dicePlacements = new Group();
    private Group specialPlacements = new Group();
    private Group scoreGroup = new Group();
    private Group b3Group = new Group();
    private Group AIScore = new Group();
    TextField textField;
    TextField textField1;
    private int roundSpecialNum = 0;
    private GridPane boardGrid;
    private GridPane exitGrid;
    private int StageNum = 0;
    String boardString = "";
    String boardString2="";
    Button button2 = new Button("New game");
    Button b3=new Button("Easy Computer Opponent");
    Button smartAI = new Button("Hard Computer Opponent");
    Label globalLabel = new Label();
    ArrayList<String> currenDice = new ArrayList<>();
    String overDiceRoll = "";



    /**
     * Authorship: Isaac
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    private void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer
        //allplacements.getChildren().clear();
        allplacements.toFront();
        if (placement != null) {
            ImageView[] img = new ImageView[placement.length()/5];
            int i = 0;
            while (placement.length() != 0) {
                String piece = placement.substring(0,5);
                String name = piece.substring(0,2);
                Image pieceImage = new Image(Viewer.class.getResource(URI_BASE + name + ".png").toString());
                img[i] = new ImageView();
                img[i].setImage(pieceImage);
                img[i].setFitHeight(70);
                img[i].setFitWidth(70);
                img[i].setTranslateX(getPosX(piece.charAt(3)));
                img[i].setTranslateY(getPosY(piece.charAt(2)));
                if (piece.charAt(4)<='3'&&piece.charAt(4)>='0'){
                    img[i].setRotate((piece.charAt(4)-'0')*90);
                }else {
                    img[i].setScaleX(-1);
                    img[i].setRotate((piece.charAt(4)-'4')*90);
                }
                allplacements.getChildren().add(img[i]);
                placement = placement.substring(5,placement.length());
            }
        }
    }

    int getPosX(char pos) {
        if (pos >= '0' && pos <= '6') {
            return (pos - '0'+1) * 70;
        }
        return 0;
    }
    int getPosY(char pos) {
        if (pos >= 'A' && pos <= 'G') {
            return (pos - 'A'+1) *70;
        }
        return 0;
    }
//Authorship： Isaac Wang
    //set the playmethod for simple AI
    private void makeAIPlacement(String placement) {
        allplacements.toFront();
        if (placement != null) {
            ImageView[] img = new ImageView[placement.length()/5];
            int i = 0;
            while (placement.length() != 0) {
                String piece = placement.substring(0,5);
                String name = piece.substring(0,2);
                Image pieceImage = new Image(Viewer.class.getResource(URI_BASE + name + ".png").toString());
                img[i] = new ImageView();
                img[i].setImage(pieceImage);
                img[i].setFitHeight(70);
                img[i].setFitWidth(70);
                img[i].setTranslateX(getAIPosX(piece.charAt(3)));
                img[i].setTranslateY(getAIPosY(piece.charAt(2)));
                if (piece.charAt(4)<='3'&&piece.charAt(4)>='0'){
                    img[i].setRotate((piece.charAt(4)-'0')*90);
                }else {
                    img[i].setScaleX(-1);
                    img[i].setRotate((piece.charAt(4)-'4')*90);
                }
                allplacements.getChildren().add(img[i]);
                placement = placement.substring(5,placement.length());
            }
        }
    }
    int getAIPosX(char pos) {
        if (pos >= '0' && pos <= '6') {
            return (pos - '0'+1) * 70+870;
        }
        return 0;
    }
    int getAIPosY(char pos) {
        if (pos >= 'A' && pos <= 'G') {
            return (pos - 'A'+1) *70;
        }
        return 0;
    }

    /**
     * Authorship: Isaac
     * Create a basic text field for input and a refresh button.
     * Note, some legacy code left in as part of the TASK 4: "FIX ME" which is no longer necessary for the game
     */
    private void makeControls() {
        /*Label label1 = new Label("Placement:");
        textField = new TextField();*/
        textField1 = new TextField();
        // textField.setPrefWidth(300);
        textField1.setPrefWidth(100);
        // Button button = new Button("Refresh");
        /*button.setOnAction(e -> {
            makePlacement(textField.getText());
           textField.clear();
        });*/
        button2.setOnAction(event -> {
            button2.setText("Round: "+(StageNum+1));
            button2.setDisable(true);
            smartAI.setDisable(true);
            globalLabel.setText("Valid moves remain and\nmust be made before\nmoving to the next round");
            dicePlacements.getChildren().clear();
            roundSpecialNum = 0;
            StageNum ++;
            String dice=RailroadInk.generateDiceRoll();
            currenDice = Tile.transToDiceArray(dice);
            overDiceRoll = dice;
            b3.setDisable(false);
            smartAI.setDisable(false);
            textField1.setText(dice);
            ImageView[] img = new ImageView[dice.length()/2];
            int i = 0;
            while (dice.length() != 0){
                String name = dice.substring(0,2);
                img[i] = new DraggableImage(name,i,660,200+100*i);
                img[i].setFitHeight(70);
                img[i].setFitWidth(70);
                dicePlacements.getChildren().add(img[i]);
                i=i+1;
                dice = dice.substring(2,dice.length());
            }
        });
            HBox hb2 =new HBox();
            hb2.getChildren().addAll(button2,textField1);
            hb2.setSpacing(10);
            hb2.setLayoutX(660);
            hb2.setLayoutY(100);
            specialButton.getChildren().add(hb2);
        HBox hb = new HBox();
        // hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }



    /**
      * Author: Ren
      * Refresh the game to initial board state
      */
    private void refreshGame(){
        boardString = "";
        roundSpecialNum = 0;
        root.getChildren().clear();
        labels.getChildren().clear();
        controls.getChildren().clear();
        allplacements.getChildren().clear();
        specialButton.getChildren().clear();
        specialPlacements.getChildren().clear();
        scoreGroup.getChildren().clear();
        dicePlacements.getChildren().clear();
        StageNum = 0;
        this.boardGrid = Grid();
        root.getChildren().add(boardGrid);

        this.exitGrid = ExitPane();
        root.getChildren().add(exitGrid);
        root.getChildren().add(controls);
        makeControls();
        root.getChildren().add(labels);
        root.getChildren().add(allplacements);
        root.getChildren().add(dicePlacements);
        placeSpecialTile();
        root.getChildren().add(specialPlacements);
        root.getChildren().add(specialButton);
        Label score = new Label();
        score.setText("Your current score is: 0 " );
        score.setLayoutX(680);
        score.setLayoutY(150);
        scoreGroup.getChildren().add(score);
        root.getChildren().add(scoreGroup);

    }


    /**
     * Author: Ren
     * Determine if the game can jump to next round.
     */
    private void canPlayNextRound(String diceRoll){
        if(StageNum > 7){
            labels.getChildren().clear();
            specialButton.getChildren().clear();
            Label message= new Label("Game Over.");
            message.setLayoutX(700);
            message.setLayoutY(20);
            labels.getChildren().add(message);
            Button finishButton = new Button("Finish Game");
            finishButton.setOnAction(event -> {
                refreshGame();
            });
            finishButton.setLayoutX(680);
            finishButton.setLayoutY(100);
            root.getChildren().addAll(finishButton);
        }else if(Tile.generateOneMove(boardString,diceRoll).isEmpty() || currenDice.isEmpty()){
            labels.getChildren().clear();
            globalLabel.setText("All regular tiles have been\nplaced. Begin the next round\nwhen ready by clicking the\nbutton below");
            globalLabel.setLayoutX(680);
            globalLabel.setLayoutY(20);
            labels.getChildren().add(globalLabel);
            button2.setDisable(false);
        }else{
            labels.getChildren().clear();
            globalLabel.setText("Valid moves remain and\nmust be made before\nmoving to the next round.");
            globalLabel.setLayoutX(680);
            globalLabel.setLayoutY(20);
            labels.getChildren().add(globalLabel);
            button2.setDisable(true);
        }
    }



    /**
     * Author: Ren
     * Inner class DraggableImage to make the tile draggable
     * Notice! Since it uses imageView, the player must click
     * right at the black part otherwise it cannot be draggable,
     * which requires the accuracy for mouse pressing.
     */
    class DraggableImage extends ImageView{
        private double mousex,mousey;
        double originalX,originalY;
        String diceRoll;
        int rotateTimes = 0;
        int i;

       DraggableImage(String diceRoll,int index,double originalX,double originalY){
           this.diceRoll  = diceRoll;
           this.i = index;
           this.originalX = originalX;
           this.originalY = originalY;
           setX(originalX);
           setY(originalY);
           Image pieceImage = new Image(Viewer.class.getResource(URI_BASE + diceRoll + ".png").toString());
           setImage(pieceImage);
           this.setOnMousePressed(event -> {
                mousex = event.getSceneX();
               mousey = event.getSceneY();
            }
            );
            this.setOnMouseDragged(event -> {
                toFront();
             double newX = event.getSceneX() - mousex;
             double newY = event.getSceneY() - mousey;
             this.setLayoutX(this.getLayoutX()+newX);
             this.setLayoutY(this.getLayoutY()+newY);
             mousex = event.getSceneX();
             mousey = event.getSceneY();
             setOpacity(0.7);
            }
            );
           this.setOnMouseReleased(event -> {
               mousex = event.getSceneX();
               mousey = event.getSceneY();
               if(canBePlaced(mousex,mousey)){
                   this.setLayoutX(1000);
                   this.setLayoutY(1000);
                   makePlacement(boardString);
                   // Clear scores for last round
                   scoreGroup.getChildren().clear();
                   Label score = new Label();
                   // If it can be placed, update the score
                   String scores = Integer.toString(RailroadInk.getAdvancedScore(boardString));
                   score.setText("Your current score is: " + scores);
                   score.setLayoutX(680);
                   score.setLayoutY(150);
                   scoreGroup.getChildren().add(score);
                   mousex = event.getSceneX();
                   mousey = event.getSceneY();
                   currenDice.remove(this.diceRoll);
                   canPlayNextRound(this.diceRoll);
                   setOpacity(1.0);
               }else{
                   this.setLayoutY(0);
                   this.setLayoutX(0);
                   mousex = event.getSceneX();
                   mousey = event.getSceneY();
                   setOpacity(1.0);
               }

           });

           setOnScroll(event -> {
               rotateTimes = (rotateTimes + 1) % 8;
               setRotate(90*rotateTimes);
               if(rotateTimes<4){
                   setScaleX(1);
               } else if(rotateTimes>=4){
                   setScaleX(-1);
               }
              // event.consume();
           });
       }


        /**
         * Author: Ren
         * Encode release state into a tile placement
         * to determine if it can be placed.
         */
        public boolean canBePlaced(double x, double y){
            boolean OutOfBoardHorizontal =  x < 70 || x > 560;
            boolean OutOfBoardVertical = y < 70 || y > 560;
            if(!OutOfBoardHorizontal || !OutOfBoardVertical){
                char row = (char) ('A' + (int) ((y - 70) / 70));
                char col = (char) ('0' + (int) ((x - 70) / 70));
                String tilePlacement = diceRoll +  row + col + (rotateTimes % 8);
                String tempBoard = boardString + tilePlacement;
                if(RailroadInk.isValidPlacementSequence(tempBoard)){
                    if(tilePlacement.charAt(0) == 'S'){
                        roundSpecialNum ++;
                        System.out.println("numbers of s :" + roundSpecialNum);
                        if(roundSpecialNum > 1) {
                            roundSpecialNum --;
                            System.out.println("Too many special tiles!");
                            return false;
                        }
                    }
                    boardString = boardString + tilePlacement;
                    System.out.println("BoardString is " + boardString);
                    return true;
                }else{
                    System.out.println("Invalid tile Placement: "+tilePlacement + " BoardString is" + boardString);
                    return false;
                }
            }
            return false;
        }

}


    //The method of establishing this simulated chessboard refers to the scheme of Miss Eilinna on github,
    // which is not original. Isaac choose to improve the method with unique parameter
    private GridPane ExitPane(){
        Image HighExit = new Image("comp1110/ass2/gui/assets/HighExit.png");
        Image RailExit = new Image("comp1110/ass2/gui/assets/RailExit.png");

        ImageView Exit02 = new ImageView(RailExit);
        ImageView Exit04 = new ImageView(HighExit);
        ImageView Exit06 = new ImageView(RailExit);
        ImageView Exit20 = new ImageView(HighExit);
        ImageView Exit40 = new ImageView(RailExit);
        ImageView Exit60 = new ImageView(HighExit);
        ImageView Exit82 = new ImageView(RailExit);
        ImageView Exit84 = new ImageView(HighExit);
        ImageView Exit86 = new ImageView(RailExit);
        ImageView Exit28 = new ImageView(HighExit);
        ImageView Exit48 = new ImageView(RailExit);
        ImageView Exit68 = new ImageView(HighExit);

        Exit02.setFitHeight(70);
        Exit02.setFitWidth(70);
        Exit04.setFitHeight(70);
        Exit04.setFitWidth(70);
        Exit06.setFitHeight(70);
        Exit06.setFitWidth(70);
        Exit20.setFitHeight(70);
        Exit20.setFitWidth(70);
        Exit40.setFitHeight(70);
        Exit40.setFitWidth(70);
        Exit60.setFitHeight(70);
        Exit60.setFitWidth(70);
        Exit82.setFitHeight(70);
        Exit82.setFitWidth(70);
        Exit84.setFitHeight(70);
        Exit84.setFitWidth(70);
        Exit86.setFitHeight(70);
        Exit86.setFitWidth(70);
        Exit28.setFitHeight(70);
        Exit28.setFitWidth(70);
        Exit48.setFitHeight(70);
        Exit48.setFitWidth(70);
        Exit68.setFitHeight(70);
        Exit68.setFitWidth(70);

        String[] exitCoordinate = {"02","04","06","20","40","60","82","84","86","28","48","68"};
        ArrayList<String> tempExit = new ArrayList<>();
        for( int k = 0; k < exitCoordinate.length; k++){
            tempExit.add(exitCoordinate[k]);
        }

        GridPane exitGrid = new GridPane();
        for (int i = 0 ; i < 9 ; i++ ) {
            for (int j = 0; j < 9; j++) {
                Pieces gridPiece = new Pieces(i,j);
                gridPiece.setPrefSize(70,70);
                String s = Integer.toString(i) + Integer.toString(j);
                if (tempExit.contains(s)) {
                    if (s.equals("02") ) {
                        Exit02.setRotate(270);
                        exitGrid.add(Exit02, i, j);
                    }
                    if (s.equals("04") ) {
                        Exit04.setRotate(270);
                        exitGrid.add(Exit04, i, j);
                    }
                    if (s.equals("06") ) {
                        Exit06.setRotate(270);
                        exitGrid.add(Exit06, i, j);
                    }
                    if (s.equals("20") ) {
                        exitGrid.add(Exit20, i, j);
                    }
                    if (s.equals("40") ) {
                        exitGrid.add(Exit40, i, j);
                    }
                    if (s.equals("60") ) {
                        exitGrid.add(Exit60, i, j);
                    }
                    if (s.equals("82") ) {
                        Exit82.setRotate(90);
                        exitGrid.add(Exit82, i, j);
                    }
                    if (s.equals("84") ) {
                        Exit84.setRotate(90);
                        exitGrid.add(Exit84, i, j);
                    }
                    if (s.equals("86") ) {
                        Exit86.setRotate(90);
                        exitGrid.add(Exit86, i, j);
                    }
                    if (s.equals("28") ) {
                        Exit28.setRotate(180);
                        exitGrid.add(Exit28, i, j);
                    }
                    if (s.equals("48") ) {
                        Exit48.setRotate(180);
                        exitGrid.add(Exit48, i, j);
                    }
                    if (s.equals("68") ) {
                        Exit68.setRotate(180);
                        exitGrid.add(Exit68, i, j);
                    }
                }
                else {
                    exitGrid.add(gridPiece, i, j);
                }
            }
        }
        return exitGrid;

    }

    public GridPane Grid(){
        GridPane boardGrid = new GridPane();
        for (int i = 0 ; i < 9 ; i++ ){
            for (int j = 0 ; j < 9 ; j++ ){
                Rectangle rectangle=new Rectangle(70,70);
                if( i == 0 || j == 0 || i == 8 || j == 8) {
                    String s = Integer.toString(i) + Integer.toString(j);
                    rectangle.setFill(Color.LEMONCHIFFON);
                    boardGrid.add(rectangle,i,j);
                }
                else{
                    if ((i + j) % 2 == 0) {
                        rectangle.setFill(Color.GHOSTWHITE);
                        boardGrid.add(rectangle, i, j);
                    } else {
                        rectangle.setFill(Color.GRAY);
                        boardGrid.add(rectangle, i, j);
                    }
                }
            }
        }
        boardGrid.setPrefSize(700, 700);

        return boardGrid;
    }

    class Pieces extends Pane {
        private int col;
        private int row;

        public Pieces(int col,int row){
            this.col = col;
            this.row = row;
        }

    }
    //Authorship: Ren for the special tile part
    private void placeSpecialTile(){
        ImageView[] img = new ImageView[6];
        ArrayList<Rectangle> recs = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            String name = "S" + i;
            img[i] = new DraggableImage(name,i,740,200+80*i);
            img[i].setFitHeight(70);
            img[i].setFitWidth(70);
            Rectangle r = new Rectangle();
            r.setLayoutX(720);
            r.setLayoutY(200+80*i);
            r.setHeight(72);
            r.setWidth(72);
            r.setArcHeight(10);
            r.setArcWidth(10);
            r.setFill(LIGHTGREY);
            recs.add(r);
            specialPlacements.getChildren().add(img[i]);
        }
        //for(Rectangle ri : recs){
          //  specialPlacements.getChildren().addAll(ri);
        //}
    }

    //Authorship: Isaac Wang for the user interface.
    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.setTitle("RailroadInk Game - thu15n");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to Railroad Ink, presented by Xueting Ren, Isaac Wang and Daniel Levy.\n\n");
        sb.append("The rules of the game can be found at https://gitlab.cecs.anu.edu.au/comp1110/comp1110-ass2\n\n");
        sb.append("Instructions for how to use our specific software is as follows:\n");
        sb.append("1) Click on 'Single Player' to play against only yourself, or click on 'AI opponent' to play against either our easy or hard computer opponent.\n");
        sb.append("2) Click on 'Start game' to begin the game. This will begin the first round with a fresh set of rolled tiles. \n");
        sb.append("3) Click on the tiles in the tile pane to move them onto the board. Invalid placements will be sent back to where you dragged them from.\n");
        sb.append("4) Tiles may be rotated by scrolling the mouse while clicking on the tile.\n");
        sb.append("5) Please note that some tiles have very narrow image definitions and so may be a touch finicky to grab hold of.\n");
        sb.append("6) Click on the tiles in the tile pane to move them onto the board. Invalid placements will be sent back to where you dragged them from.\n");
        sb.append("7) If you have chosen to play against the AI opponent, select your preferred mode of difficulty by clicking on 'Easy' or 'Hard'.\n");
        sb.append("8) You must make this selection before moving onto the next round or the AI move won't be recorded.\n");
        sb.append("9) You can choose either easy or hard AI moves each round, with no obligation to stick to your last choice.\n");
        sb.append("10) Have fun!\n");
        Text instructions = new Text();
        String sb1 = sb.toString();
        instructions.setText(sb1);
        instructions.setLayoutX(VIEWER_WIDTH / 2 - 350);
        instructions.setLayoutY(VIEWER_HEIGHT / 2 - 200);
        root.getChildren().add(instructions);
        Button button = new Button("Single Player");
        button.setLayoutX(VIEWER_WIDTH / 2 - 50);
        button.setLayoutY(VIEWER_HEIGHT / 2 + 160);
        Button button2 = new Button("AI opponent");
        button2.setLayoutX(VIEWER_WIDTH / 2 - 50);
        button2.setLayoutY(VIEWER_HEIGHT / 2 + 220);
        //this part is for single players
        button.setOnAction(e -> {
            root.getChildren().remove(instructions);
            this.boardGrid = Grid();
            root.getChildren().add(boardGrid);

            this.exitGrid = ExitPane();
            root.getChildren().add(exitGrid);
            root.getChildren().add(controls);
            makeControls();
            root.getChildren().add(labels);
            root.getChildren().add(allplacements);
            root.getChildren().add(dicePlacements);
            placeSpecialTile();
            root.getChildren().add(specialPlacements);
            root.getChildren().add(specialButton);
            Label score = new Label();
            score.setText("Your current score is: 0" );
            score.setLayoutX(680);
            score.setLayoutY(150);
            scoreGroup.getChildren().add(score);
            root.getChildren().add(scoreGroup);
            primaryStage.setScene(scene);

            primaryStage.show();

        });
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // When a player is playing with AI, they cannot play single pattern.
                root.getChildren().remove(instructions);
                button.setDisable(true);
                Stage secondStage = new Stage();
                BorderPane border =new BorderPane();
                Scene secondScene = new Scene(border, 1500, 768);
                border.getChildren().add(Grid());
                border.setLeft(ExitPane());
                border.getChildren().add(controls);
                makeControls();
                border.getChildren().add(labels);
                border.getChildren().add(allplacements);
                border.getChildren().add(dicePlacements);
                placeSpecialTile();
                border.getChildren().add(specialPlacements);
                border.getChildren().add(specialButton);
                Label score = new Label();
                score.setText("Your current score is: 0 " );
                score.setLayoutX(680);
                score.setLayoutY(150);
                scoreGroup.getChildren().add(score);
                border.getChildren().add(scoreGroup);
                GridPane Grid2=Grid();
                border.getChildren().add(Grid2);
                Grid2.setLayoutX(870);
                border.setRight(ExitPane());
                b3.setLayoutX(900);
                b3.setLayoutY(700);
                b3.toFront();
                smartAI.setLayoutX(1200);
                smartAI.setLayoutY(700);
                smartAI.toFront();
                b3Group.getChildren().add(b3);
                b3Group.getChildren().add(smartAI);
                Label AIs = new Label();
                AIs.setText("AI current score is: 0");
                AIs.setLayoutX(680);
                AIs.setLayoutY(180);
                AIScore.getChildren().add(AIs);
                border.getChildren().add(AIScore);
                border.getChildren().add(b3Group);
                if(overDiceRoll == ""){
                    b3.setDisable(true);
                    smartAI.setDisable(true);
                }
                b3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        b3.setDisable(true);
                        smartAI.setDisable(true);
                        String a=AI.SimpleAIMove(boardString2,overDiceRoll);
                        boardString2=boardString2+a;
                        makeAIPlacement(a);
                        overDiceRoll = "";

                        // For showing score of AI
                        AIScore.getChildren().clear();
                        Label AIscores = new Label();
                        // update the score
                        String AIscore = Integer.toString(RailroadInk.getAdvancedScore(boardString2));
                        AIscores.setText("AI current score is: " + AIscore);
                        AIscores.setLayoutX(700);
                        AIscores.setLayoutY(180);
                        AIScore.getChildren().add(AIscores);
                    }
                });
                smartAI.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        smartAI.setDisable(true);
                        b3.setDisable(true);
                        String a=AI.AdvancedAIMove(boardString2,overDiceRoll);
                        boardString2=boardString2+a;
                        makeAIPlacement(a);
                        overDiceRoll = "";  // clear overDiceRoll
//                         For showing score of AI
                        AIScore.getChildren().clear();
                        Label AIscores = new Label();
                        // update the score
                        String AIscore = Integer.toString(RailroadInk.getAdvancedScore(boardString2));
                        AIscores.setText("AI current score is: " + AIscore);
                        AIscores.setLayoutX(680);
                        AIscores.setLayoutY(180);
                        AIScore.getChildren().add(AIscores);
                    }
                });
                // As the player closed the AI playing window
                // the single player button can be pressed
                secondStage.setOnCloseRequest( event -> {
                    button.setDisable(false);} );
                secondStage.setScene(secondScene);
                secondStage.show();
              //  secondStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                  //  @Override
                 //   public void handle(WindowEvent windowEvent) {
                 //       Platform.exit();
                  //  }
               // });
            }
        });
        //set the exit logic that when you close
        // the main window you can exit the whole
        //program
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
       // button2.setOnAction(e -> {

       // });

        root.getChildren().add(button);
        root.getChildren().add(button2);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
