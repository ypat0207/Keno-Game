import java.io.FileInputStream;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane; 
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.paint.ImagePattern; 
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class mainApp extends Application {
    
    private Button playButton;	
    private Button startDrawButton;	
    private Button chooseRandomButton;
    private Button resetGridButton;
    private Button nextDrawButton;
    private Button finishButton;
    private Button exitButton;
    private Button rulesBackButton;
    private Button oddsBackButton;
    private MenuBar menuBar;
    private GridPane gameBoard;
    private GridPane randomBoard;
    private ComboBox<Integer> spotsBox;
    private ComboBox<Integer> drawsBox;
    private Image logoImg;
    private ImageView logoImgView;
    private kenoGame game;
    private betCard card;
    private EventHandler<ActionEvent> GPHandler;
    private Integer valueOfSpots;
    private Integer valueOfDrawings;
    private Double total;
    private int numOfCurrDraw;
    private Scene prevScene;
    private Scene currScene;
    private Image gameSceneImg;
    

    public static void main(String[] args) {
	launch(args);
    }
    
    private void initGame() {
	// TODO
    }

    private void showDraws() {
	// TODO
    }

    private void changeLook() {
    	Random rand = new Random();
	    float red = rand.nextFloat();
	    float green = rand.nextFloat();
	    float blue = rand.nextFloat();
	    int alpha = rand.nextInt(3);
	    Color randomColor = new Color(red, green, blue, 0.5);
	    BackgroundFill backgroundFill = new BackgroundFill(randomColor, null, null);
        Background background = new Background(backgroundFill);
	// TODO
    }

    private void addGrid(GridPane grid) {
	
	grid.setPadding(new Insets(40, 50, 1, 50));
    	grid.setHgap(1);
    	grid.setVgap(2);
    	
	for (int i = 0; i < 8; i++) {
	    for (int j = 1; j < 11; j++) {
		Button b = new Button(Integer.toString((i * 10) + j));
		b.setOnAction(GPHandler);
    		b.setPrefHeight(35);
    		b.setPrefWidth(37);
    		b.setPrefSize(80, 50);
    		b.setShape(new Rectangle(60, 60));
		b.setStyle("-fx-background-color: \n"
    				+ "        #800000	,\n"
    				+ "        rgba(10,0,0,0.05),\n"
    				+ "        linear-gradient(#cca30e, #cca30e),\n"
    				+ "        linear-gradient(#CD853F 200%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
    				+ "        linear-gradient(#e0bb34, #e6c34d);\n"
    				+ "    -fx-background-insets: 0,9 9 8 9,9,1,3;\n"
    				+ "    -fx-background-radius: 0;\n"
    				+ "    -fx-padding: 15 15 15 15;\n"
    				+ "    -fx-font-family: \"Helvetica\";\n"
    				+ "    -fx-font-size: 14px;\n"
    				+ "    -fx-text-fill: #311c09;\n"
    				+ "    -fx-effect: innershadow( three-pass-box , rgba(10,20,50,0.1) , 2, 0.0 , 0 , 1); -fx-font-weight: bold;");
		grid.add(b, j, i);
	    }
	} 
    }
    private Scene cardSceneFunc() {
	card = new betCard();
	game = new kenoGame();

	spotsBox = new ComboBox<Integer>();
	spotsBox.setPrefSize(160, 25);
	spotsBox.setPromptText("# of spots");
	spotsBox.getItems().add(1);
	spotsBox.getItems().add(4);
	spotsBox.getItems().add(8);
	spotsBox.getItems().add(10);
	spotsBox.setStyle("-fx-background-color: gold; -fx-color: gold;");

	drawsBox = new ComboBox<Integer>();
	drawsBox.setPrefSize(160, 25);
	drawsBox.setPromptText("# of draws");
	drawsBox.getItems().add(1);
	drawsBox.getItems().add(2);
	drawsBox.getItems().add(3);
	drawsBox.getItems().add(4);
	drawsBox.setStyle("-fx-background-color: gold; -fx-color: gold;");

	resetGridButton = new Button("Reset Grid");
	resetGridButton.setDisable(true);
	resetGridButton.setStyle("-fx-background-color: gold");
	resetGridButton.setPrefSize(160, 25);
		
	Label numberOfSpots = new Label(" Number of spots: \n");
	numberOfSpots.setStyle("-fx-text-fill: gold;-fx-font: bold 16px 'Arial'; ");

	Label numberOfDrawings = new Label(" Number of draws: \n");
	numberOfDrawings.setStyle("-fx-text-fill: gold;-fx-font: bold 16px 'Arial';");
	
	GPHandler = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		if (card.getChosenSpots().size() < card.getNumSpots()) {
			// TODO - Possibility of unselecting items
			card.addChosenSpots(Integer.valueOf(((Button)e.getSource()).getText()));
			((Button)e.getSource()).setStyle("-fx-background-color: \n"
    					+ "        #800000	,\n"
    					+ "        rgba(10,0,0,0.05),\n"
    					+ "        linear-gradient(#cca30e, #cca30e),\n"
    					+ "        linear-gradient(#CD853F 200%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
    					+ "        linear-gradient(#e03434, #e64d4d);\n"
    					+ "    -fx-background-insets: 0, 9 9 8 9, 9, 1, 3;\n"
    					+ "    -fx-background-radius: 0;\n"
    					+ "    -fx-padding: 15 15 15 15;\n"
    					+ "    -fx-font-family: \"Helvetica\";\n"
    					+ "    -fx-font-size: 14px;\n"
    					+ "    -fx-text-fill: #ede9e6;\n"
    					+ "    -fx-effect: innershadow( three-pass-box , rgba(10,20,50,0.1) , 2, 0.0 , 0 , 1); -fx-font-weight: bold;");
		}
	    }
	};

	gameBoard = new GridPane();
	gameBoard.setAlignment(Pos.CENTER);
	addGrid(gameBoard);
	gameBoard.setDisable(true);

	chooseRandomButton = new Button("Choose Random");
	chooseRandomButton.setDisable(true);
	chooseRandomButton.setPrefSize(300, 75);
	chooseRandomButton.setStyle("-fx-background-color: \n"
			+ " #ecebe9,\n"
			+ " rgba(0,0,0,0.05),\n"
			+ " linear-gradient(#cca30e, #cca30e),\n"
			+ " linear-gradient(#CD853F 100%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
			+ " linear-gradient(#e0bb34, #e6c34d);\n"
			+ " -fx-background-insets: 0,9 9 8 9,9,0,6;\n"
			+ " -fx-background-radius: 50;\n"
			+ " -fx-padding: 15 30 15 30;\n"
			+ " -fx-font-family: \"Helvetica\";\n"
			+ " -fx-font-size: 18px;\n"
			+ " -fx-text-fill: #311c09;\n"
			+ " -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);"
			+ "-fx-font: bold 24px 'Arial';");
	
	Region cardSceneSpacer = new Region();
	HBox.setHgrow(cardSceneSpacer, Priority.ALWAYS);
	
	startDrawButton = new Button("Start Draw");
	startDrawButton.setPrefSize(300, 75);
	startDrawButton.setDisable(true);
	startDrawButton.setStyle("-fx-background-color: \n"
			+ " #ecebe9,\n"
			+ " rgba(0,0,0,0.05),\n"
			+ " linear-gradient(#cca30e, #cca30e),\n"
			+ " linear-gradient(#CD853F 100%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
			+ " linear-gradient(#e0bb34, #e6c34d);\n"
			+ " -fx-background-insets: 0,9 9 8 9,9,0,6;\n"
			+ " -fx-background-radius: 50;\n"
			+ " -fx-padding: 15 30 15 30;\n"
			+ " -fx-font-family: \"Helvetica\";\n"
			+ " -fx-font-size: 18px;\n"
			+ " -fx-text-fill: #311c09;\n"
			+ " -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);"
			+ "-fx-font: bold 24px 'Arial';");

	spotsBox.setOnAction(e-> {
		valueOfSpots = spotsBox.getSelectionModel().getSelectedItem();
		card.setNumSpots(valueOfSpots);
		if((valueOfDrawings != null) && (valueOfSpots != null)) {
			resetGridButton.setDisable(false);
			gameBoard.setDisable(false);
			chooseRandomButton.setDisable(false);
			startDrawButton.setDisable(false);
		}

	});
	
	drawsBox.setOnAction(e-> {
		valueOfDrawings = drawsBox.getSelectionModel().getSelectedItem();
		card.setNumDraws(valueOfDrawings);
		if((valueOfDrawings != null) && (valueOfSpots != null)) {
			resetGridButton.setDisable(false);
			gameBoard.setDisable(false);
			chooseRandomButton.setDisable(false);
			startDrawButton.setDisable(false);
		}
	});

	chooseRandomButton.setOnAction(e -> {
		card.chooseRandom();
		for (Node node : gameBoard.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (button.isDisabled()) {
                    button.setStyle("-fx-background-color: gray;");
                }
            }
		
		// TODO - Highlight the randomly selected buttons.
		}
	});
	

	resetGridButton.setOnAction(reset -> {
	    for (Node node : gameBoard.getChildren()) {
		card.clearChosenSpots();
		node.setStyle("-fx-background-color: \n"
    				+ "        #800000	,\n"
    				+ "        rgba(10,0,0,0.05),\n"
    				+ "        linear-gradient(#cca30e, #cca30e),\n"
    				+ "        linear-gradient(#CD853F 200%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
    				+ "        linear-gradient(#e0bb34, #e6c34d);\n"
    				+ "    -fx-background-insets: 0,9 9 8 9,9,1,3;\n"
    				+ "    -fx-background-radius: 0;\n"
    				+ "    -fx-padding: 15 15 15 15;\n"
    				+ "    -fx-font-family: \"Helvetica\";\n"
    				+ "    -fx-font-size: 14px;\n"
    				+ "    -fx-text-fill: #311c09;\n"
    				+ "    -fx-effect: innershadow( three-pass-box , rgba(10,20,50,0.1) , 2, 0.0 , 0 , 1); -fx-font-weight: bold;");

	    }
	});

	HBox numSpotsHB = new HBox();	
	numSpotsHB.setPadding(new Insets(10, 100, 10, 250));
	numSpotsHB.getChildren().addAll(numberOfSpots,spotsBox);
	numSpotsHB.setAlignment(Pos.CENTER);

	HBox numDrawsHB = new HBox();
	numDrawsHB.setPadding(new Insets(10, 100, 10, 250));
	numDrawsHB.setAlignment(Pos.CENTER);
	numDrawsHB.getChildren().addAll(numberOfDrawings,drawsBox);
	
	HBox resetBox = new HBox();
	resetBox.setPadding(new Insets(10, 10, 0, 580));
	resetBox.getChildren().add(resetGridButton);	

	HBox cardSceneButtonsHB = new HBox();
	cardSceneButtonsHB.setPadding(new Insets(20, 10, 20, 10));
	cardSceneButtonsHB.getChildren().addAll(chooseRandomButton, cardSceneSpacer, startDrawButton);
	
	VBox cardSceneVB = new VBox(menuBar, numSpotsHB, numDrawsHB, resetBox, gameBoard, cardSceneButtonsHB);
	try {
	    FileInputStream cardSceneBG = new FileInputStream("src/main/resource/images/casino.jpeg");
	    Image cardSceneImg = new Image(cardSceneBG);
	    ImageView cardSceneImgView = new ImageView();
	    cardSceneImgView.setImage(cardSceneImg);
	    ImagePattern cardScenepattern = new ImagePattern(cardSceneImg);
	    cardSceneVB.setBackground(new Background(new BackgroundImage(cardSceneImg,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false))));
	} catch (Exception e) {
	    System.out.println("An exception occurred!");
	}
	Scene cardScene = new Scene(cardSceneVB, 1000, 700);
	return cardScene;

    }

    private Scene gameSceneFunc() {
	numOfCurrDraw++;
	game.clearDrawNums();
	game.setSpotsMatched(0);
	game.clearCurrentPayout();
	game.playRound(card);
	total += game.getCurrentPayout();

	Label totalPrizeLabel = new Label(" Total prize so far: $");
	totalPrizeLabel.setStyle("-fx-text-fill: gold;-fx-font: bold 16px 'Arial'; ");
	Label totalPrize = new Label(Double.toString(total));
	totalPrize.setStyle("-fx-text-fill: gold;-fx-font: bold 16px 'Arial'; ");

	randomBoard = new GridPane();
	randomBoard.setAlignment(Pos.CENTER);
	
	randomBoard.setPadding(new Insets(40, 50, 1, 50));
    	randomBoard.setHgap(1);
    	randomBoard.setVgap(2);
    	int col = 0;
	int row = 0;
	for (Integer i : game.getDrawNums()) {
	    
	    Label l = new Label(Integer.toString(i));
    	    l.setPrefHeight(35);
    	    l.setPrefWidth(35);
    	    l.setPrefSize(80, 50);
    	    l.setShape(new Rectangle(60, 60));
	    if (card.getChosenSpots().contains(i)) {
			l.setStyle("-fx-background-color: \n"
    				+ "        #800000	,\n"
    				+ "        rgba(10,0,0,0.05),\n"
    				+ "        linear-gradient(#cca30e, #cca30e),\n"
    				+ "        linear-gradient(#CD853F 200%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
    				+ "        linear-gradient(#e03434, #e64d4d);\n"
    				+ "    -fx-background-insets: 0, 9 9 8 9, 9, 1, 3;\n"
    				+ "    -fx-background-radius: 0;\n"
    				+ "    -fx-padding: 15 15 15 15;\n"
    				+ "    -fx-font-family: \"Helvetica\";\n"
    				+ "    -fx-font-size: 14px;\n"
    				+ "    -fx-text-fill: #ede9e6;\n"
    				+ "    -fx-effect: innershadow( three-pass-box , rgba(10,20,50,0.1) , 2, 0.0 , 0 , 1); -fx-font-weight: bold;");
	    } else {
	    l.setStyle("-fx-background-color: \n"
			    + "        #800000	,\n"
    			    + "        rgba(10,0,0,0.05),\n"
    			    + "        linear-gradient(#cca30e, #cca30e),\n"
    			    + "        linear-gradient(#CD853F 200%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
    			    + "        linear-gradient(#e0bb34, #e6c34d);\n"
    			    + "    -fx-background-insets: 0,9 9 8 9,9,1,3;\n"
    			    + "    -fx-background-radius: 0;\n"
    			    + "    -fx-padding: 15 15 15 15;\n"
    			    + "    -fx-font-family: \"Helvetica\";\n"
    			    + "    -fx-font-size: 14px;\n"
    			    + "    -fx-text-fill: #311c09;\n"
    			    + "    -fx-effect: innershadow( three-pass-box , rgba(10,20,50,0.1) , 2, 0.0 , 0 , 1); -fx-font-weight: bold;");}
	    randomBoard.add(l, col, row);
	    col++;
	    if (col > 9) {
		row++;
		col = 0;
	    }
	}

	Label numMatchedLabel = new Label(" Numbers Matched: \n");
	numMatchedLabel.setStyle("-fx-text-fill: gold;-fx-font: bold 16px 'Arial'; ");
	Label numMatched = new Label(Integer.toString(game.getSpotsMatched()));
	numMatched.setStyle("-fx-text-fill: gold;-fx-font: bold 16px 'Arial'; ");

	Label moneyWonLabel = new Label(" Money won this round: $");
	moneyWonLabel.setStyle("-fx-text-fill: gold;-fx-font: bold 16px 'Arial';");
	Label moneyWon = new Label(Double.toString(game.getCurrentPayout()));
	moneyWon.setStyle("-fx-text-fill: gold;-fx-font: bold 16px 'Arial';");
	
	Region gameSceneSpacer = new Region();
	HBox.setHgrow(gameSceneSpacer, Priority.ALWAYS);

	startDrawButton.setText("Next draw");		
	HBox numMatchHB = new HBox();	
	numMatchHB.setPadding(new Insets(25, 100, 10, 250));
	numMatchHB.getChildren().addAll(numMatchedLabel, numMatched);
	numMatchHB.setAlignment(Pos.CENTER);

	HBox moneyWonHB = new HBox();
	moneyWonHB.setPadding(new Insets(10, 100, 10, 250));
	moneyWonHB.setAlignment(Pos.CENTER);
	moneyWonHB.getChildren().addAll(moneyWonLabel, moneyWon);
	
	HBox totalPrizeHB = new HBox();
	totalPrizeHB.setPadding(new Insets(10, 10, 0, 580));
	totalPrizeHB.getChildren().addAll(totalPrizeLabel, totalPrize);	

	HBox gameSceneButtonsHB = new HBox();
	gameSceneButtonsHB.setPadding(new Insets(20, 10, 20, 10));
	if (numOfCurrDraw != card.getNumDraws()) {
	    gameSceneButtonsHB.getChildren().addAll(gameSceneSpacer, startDrawButton);
	} else {
	    gameSceneButtonsHB.getChildren().addAll(gameSceneSpacer, finishButton);
	}
	
	VBox gameSceneVB = new VBox(menuBar, totalPrizeHB, randomBoard, numMatchHB, moneyWonHB, gameSceneButtonsHB);
	try {
	    FileInputStream gameSceneBG = new FileInputStream("src/main/resource/images/casino.jpeg");
	    Image gameSceneImg = new Image(gameSceneBG);
	    ImageView gameSceneImgView = new ImageView();
	    gameSceneImgView.setImage(gameSceneImg);
	    ImagePattern gameScenepattern = new ImagePattern(gameSceneImg);
	    gameSceneVB.setBackground(new Background(new BackgroundImage(gameSceneImg,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false))));

	} catch (Exception e){
	    System.out.println("An exception occured!");
	}
	
	Scene gameScene = new Scene(gameSceneVB, 1000, 700);
	return gameScene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	primaryStage.setTitle("Keno");
	prevScene = null;
	currScene = null;
	total = 0.0;
	numOfCurrDraw = 0;

	// Rules scene
	Text rules = new Text();
	rules.setText("Rules:\n"
			+ "1) Select number of spots and number of draws.\n"
			+ "2) You will not be allowed to pick spots unless you do so.\n"
			+ "3) Select spots on the grid or click 'Choose Random' to have the program do it for you.\n"
			+ "4) Click on 'Start Draw'. The program will play the game for the number of draws you selected\n"
			+ " and show you your prize money. Once all draws are completed, you will be given the chance to play again, or exit.\n");
	rules.setStyle("-fx-text-fill: gold;-fx-font: bold 16px 'Arial'; ");
	
	rulesBackButton = new Button("Go back");
	rulesBackButton.setPrefSize(300, 75);
	rulesBackButton.setStyle("-fx-background-color: \n"
			+ " #ecebe9,\n"
			+ " rgba(0,0,0,0.05),\n"
			+ " linear-gradient(#cca30e, #cca30e),\n"
			+ " linear-gradient(#CD853F 100%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
			+ " linear-gradient(#e0bb34, #e6c34d);\n"
			+ " -fx-background-insets: 0,9 9 8 9,9,0,6;\n"
			+ " -fx-background-radius: 50;\n"
			+ " -fx-padding: 15 30 15 30;\n"
			+ " -fx-font-family: \"Helvetica\";\n"
			+ " -fx-font-size: 18px;\n"
			+ " -fx-text-fill: #311c09;\n"
			+ " -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);"
			+ "-fx-font: bold 24px 'Arial';");

	rulesBackButton.setOnAction(e -> {
		primaryStage.setScene(prevScene);
	});
	
	VBox rulesVB = new VBox(rules, rulesBackButton);
	Scene rulesScene = new Scene(rulesVB, 1000, 700);

	// Odds scene
	Text odds = new Text();
	odds.setText("Odds:\n"
			+ "1 draw: 1 spot => $2.\n"
			+ "4 draws: 2 spots => $1, 3 spots => $5, 4 spots => $75.\n"
			+ "8 draws: 4 spots => $2, 5 spots => $12, 6 spots => $50, 7 spots => $750, 8 spots => $10,000.\n"
			+ "10 draws: 0 spots => $5, 5 spots => $2, 6 spots => $15, 7 spots => $40, 8 spots => $450, 9 spots => $4250, 10 spots => $100,000.\n");

	odds.setStyle("-fx-text-fill: gold;-fx-font: bold 16px 'Arial'; ");
	
	oddsBackButton = new Button("Go back");
	oddsBackButton.setPrefSize(300, 75);
	oddsBackButton.setStyle("-fx-background-color: \n"
			+ " #ecebe9,\n"
			+ " rgba(0,0,0,0.05),\n"
			+ " linear-gradient(#cca30e, #cca30e),\n"
			+ " linear-gradient(#CD853F 100%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
			+ " linear-gradient(#e0bb34, #e6c34d);\n"
			+ " -fx-background-insets: 0,9 9 8 9,9,0,6;\n"
			+ " -fx-background-radius: 50;\n"
			+ " -fx-padding: 15 30 15 30;\n"
			+ " -fx-font-family: \"Helvetica\";\n"
			+ " -fx-font-size: 18px;\n"
			+ " -fx-text-fill: #311c09;\n"
			+ " -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);"
			+ "-fx-font: bold 24px 'Arial';");

	oddsBackButton.setOnAction(e -> {
		primaryStage.setScene(prevScene);
	});

	VBox oddsVB = new VBox(odds, oddsBackButton);
	Scene oddsScene = new Scene(oddsVB, 1000, 700);

	menuBar = new MenuBar();
	Menu menu = new Menu("Menu");
	MenuItem iRules = new MenuItem("Display rules");
	iRules.setOnAction(e -> {
		primaryStage.setScene(rulesScene);
		prevScene = currScene;
	});
	
	MenuItem iOdds = new MenuItem("Display odds");
	iOdds.setOnAction(e -> {
		primaryStage.setScene(oddsScene);
		prevScene = currScene;
	});

	MenuItem iLook = new MenuItem("Change look");
	iLook.setOnAction(e-> {
		changeLook();
	});
	
	MenuItem iExit = new MenuItem("Exit");
	iExit.setOnAction(e -> Platform.exit());
	
	menu.getItems().addAll(iRules, iOdds, iLook,iExit);
	menuBar.getMenus().addAll(menu);

	// Start scene
	
	BorderPane startScreenPane = new BorderPane();
	startScreenPane.setPrefSize(900, 900);
	FileInputStream imageStream = new FileInputStream("src/main/resource/images/keno.png");
	logoImg = new Image(imageStream);
	logoImgView = new ImageView(logoImg);
	logoImgView.setFitHeight(300);
	logoImgView.setFitWidth(300);
	startScreenPane.setCenter(logoImgView);
	playButton = new Button();
	playButton.setPrefSize(300, 75);
	playButton.setText("Play Game");

	playButton.setStyle("-fx-background-color: \n"
			+ "        #ecebe9,\n"
			+ "        rgba(0,0,0,0.05),\n"
			+ "        linear-gradient(#cca30e, #cca30e),\n"
			+ "        linear-gradient(#CD853F 100%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
			+ "        linear-gradient(#e0bb34, #e6c34d);\n"
			+ "    -fx-background-insets: 0,9 9 8 9,9,0,6;\n"
			+ "    -fx-background-radius: 50;\n"
			+ "    -fx-padding: 15 30 15 30;\n"
			+ "    -fx-font-family: \"Helvetica\";\n"
			+ "    -fx-font-size: 18px;\n"
			+ "    -fx-text-fill: #311c09;\n"
			+ "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);");

	// set the padding of button1 to move it a little higher
	startScreenPane.setBottom(playButton);
	//startScreenPane.setTop(menuBar);
	HBox hb1 = new HBox();
	hb1.getChildren().add(playButton);
	hb1.setAlignment(Pos.TOP_CENTER);
	hb1.setPadding(new Insets(0, 10, 50, 10));

	startScreenPane.setBottom(hb1);
	FileInputStream background = new FileInputStream("src/main/resource/images/WealthyBitterBlackbear-max-1mb.gif");
	Image image2 = new Image(background);
	ImageView backgroundImage = new ImageView();
	backgroundImage.setImage(image2);
	VBox vb1 = new VBox(startScreenPane);
	Scene startScene = new Scene(vb1, 1000, 700);

	startScreenPane.setBackground(new Background(new BackgroundImage(image2,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1.0, 1.0, true, true, false, false))));
	
	
	ImagePattern pattern = new ImagePattern(image2);
	startScene.setFill(pattern);	
	
	
	// Card scene
	Scene cardScene = cardSceneFunc();

	// End scene
	
	BorderPane endSceneBorderPane = new BorderPane();
	endSceneBorderPane.setPrefSize(900, 900);
	Button playAgain = new Button("Play Again");
	playAgain.setPrefSize(300, 75);
	playAgain.setStyle("-fx-background-color: \n"
			+ "        #ecebe9,\n"
			+ "        rgba(0,0,0,0.05),\n"
			+ "        linear-gradient(#cca30e, #cca30e),\n"
			+ "        linear-gradient(#CD853F 100%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
			+ "        linear-gradient(#e0bb34, #e6c34d);\n"
			+ "    -fx-background-insets: 0,9 9 8 9,9,0,6;\n"
			+ "    -fx-background-radius: 50;\n"
			+ "    -fx-padding: 15 30 15 30;\n"
			+ "    -fx-font-family: \"Helvetica\";\n"
			+ "    -fx-font-size: 18px;\n"
			+ "    -fx-text-fill: #311c09;\n"
			+ "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);");
	exitButton = new Button("Exit");
	exitButton.setPrefSize(300, 75);
	exitButton.setStyle("-fx-background-color: \n"
			+ "        #ecebe9,\n"
			+ "        rgba(0,0,0,0.05),\n"
			+ "        linear-gradient(#cca30e, #cca30e),\n"
			+ "        linear-gradient(#CD853F 100%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
			+ "        linear-gradient(#e0bb34, #e6c34d);\n"
			+ "    -fx-background-insets: 0,9 9 8 9,9,0,6;\n"
			+ "    -fx-background-radius: 50;\n"
			+ "    -fx-padding: 15 30 15 30;\n"
			+ "    -fx-font-family: \"Helvetica\";\n"
			+ "    -fx-font-size: 18px;\n"
			+ "    -fx-text-fill: #311c09;\n"
			+ "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);");
	
	StackPane endSceneStackPane = new StackPane();
	StackPane endSceneImagePane = new StackPane();
	HBox hbox2 = new HBox();
	hbox2.setPadding(new Insets(0, 10, 20, 10));
	
	HBox.setHgrow(playAgain, Priority.ALWAYS);
	HBox.setHgrow(exitButton, Priority.ALWAYS);
	Region region1 = new Region();
	Region region2 = new Region();
	HBox.setHgrow(region1, Priority.SOMETIMES);
	HBox.setHgrow(region2, Priority.SOMETIMES);
	StackPane.setAlignment(region1, Pos.CENTER_LEFT);
	StackPane.setAlignment(region2, Pos.CENTER_RIGHT);
	hbox2.getChildren().addAll(playAgain,region1, region2, exitButton);
	endSceneStackPane.getChildren().add(hbox2);
	endSceneBorderPane.setBottom(endSceneStackPane);
	
	

	FileInputStream scene2Background = new FileInputStream("src/main/resource/images/casino.jpeg");
	Image scene2Image = new Image(scene2Background);
	ImageView scene2ImageView = new ImageView();
	scene2ImageView.setImage(scene2Image);
	endSceneBorderPane.setBackground(new Background(new BackgroundImage(scene2Image,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1.0, 1.0, true, true, false, false))));
	
	Label drawingsCompleteLabel = new Label("Drawings Complete");
	drawingsCompleteLabel.setPadding(new Insets(0, 10, 180, 10));
	Label moneyWonLabel = new Label("Money Won: $" + game.getCurrentPayout());
	moneyWonLabel.setPadding(new Insets(0, 10, 180, 0));
	drawingsCompleteLabel.setStyle("-fx-font-family: Calligraffitti; -fx-font-weight: 500;"
			+ "-fx-font-size: 70px;-fx-text-fill: #CD853F;\n"
			+ "-fx-effect: dropshadow(gaussian, #FFD700		\n"
			+ ", 2, 5, 2, 1);");
	moneyWonLabel.setStyle("-fx-font-family: Calligraffitti; -fx-font-weight: 500;"
			+ "-fx-font-size: 70px;-fx-text-fill: #CD853F;\n"
			+ "-fx-effect: dropshadow(gaussian, #FFD700		\n"
			+ ", 2, 5, 2, 1);");
	VBox endSceneVbox = new VBox();
	endSceneVbox.setAlignment(Pos.CENTER);	
	endSceneVbox.setPadding(new Insets(0, 10, 0, 0));
	endSceneVbox.getChildren().addAll(drawingsCompleteLabel,moneyWonLabel);
	
	endSceneBorderPane.setCenter(endSceneVbox);
	
	

	VBox vb4 = new VBox(endSceneBorderPane);
	Scene endScene = new Scene(vb4, 1000, 700);

	exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        	Platform.exit();        	
            }
    	});

	playAgain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        	spotsBox.setValue(0);
        	drawsBox.setValue(0);
        	card.clearChosenSpots();
		for (Node node : gameBoard.getChildren()) {
			card.clearChosenSpots();
			node.setStyle("-fx-background-color: \n"
    				+ "        #800000	,\n"
    				+ "        rgba(10,0,0,0.05),\n"
    				+ "        linear-gradient(#cca30e, #cca30e),\n"
    				+ "        linear-gradient(#CD853F 200%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
    				+ "        linear-gradient(#e0bb34, #e6c34d);\n"
    				+ "    -fx-background-insets: 0,9 9 8 9,9,1,3;\n"
    				+ "    -fx-background-radius: 0;\n"
    				+ "    -fx-padding: 15 15 15 15;\n"
    				+ "    -fx-font-family: \"Helvetica\";\n"
    				+ "    -fx-font-size: 14px;\n"
    				+ "    -fx-text-fill: #311c09;\n"
    				+ "    -fx-effect: innershadow( three-pass-box , rgba(10,20,50,0.1) , 2, 0.0 , 0 , 1); -fx-font-weight: bold;");

	    	}
		Scene cardScene = cardSceneFunc();
        	primaryStage.setScene(cardScene);
            }
        });

	
	finishButton = new Button("Finish");
	finishButton.setPrefSize(300, 75);
	finishButton.setStyle("-fx-background-color: \n"
			+ " #ecebe9,\n"
			+ " rgba(0,0,0,0.05),\n"
			+ " linear-gradient(#cca30e, #cca30e),\n"
			+ " linear-gradient(#CD853F 100%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
			+ " linear-gradient(#e0bb34, #e6c34d);\n"
			+ " -fx-background-insets: 0,9 9 8 9,9,0,6;\n"
			+ " -fx-background-radius: 50;\n"
			+ " -fx-padding: 15 30 15 30;\n"
			+ " -fx-font-family: \"Helvetica\";\n"
			+ " -fx-font-size: 18px;\n"
			+ " -fx-text-fill: #311c09;\n"
			+ " -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);"
			+ "-fx-font: bold 24px 'Arial';");
	
	playButton.setOnAction(e -> {
		primaryStage.setScene(cardScene);
		currScene = cardScene;
	});

	finishButton.setOnAction(e -> {
		primaryStage.setScene(endScene);
		currScene = endScene;
	});

	startDrawButton.setOnAction(e -> {
		Scene gScene = gameSceneFunc();
		primaryStage.setScene(gScene);
		currScene = gScene;
	});
	
	primaryStage.setScene(startScene);
	currScene = startScene;
	primaryStage.show();
		
	}

}