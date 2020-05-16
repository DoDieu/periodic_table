/*
 *
 * プログラミング演習B  2018年 最終プロジェクト課題用 原型プログラム 
 *
 *　作品名：　元素の周期表の勉強
 *　作成者：ドティバン　ジェウ DoDieu
 */

import java.util.Optional;
import javafx.application.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import static javafx.scene.layout.BackgroundRepeat.*;
import javafx.scene.text.*;


public class Project2018a extends Application {

    private Label lb; // 画面上部のラベル
    private AnchorPane ap0;// 大元のPane,開始画面Pane
    private BorderPane bp;  //　パネルを切り替えるための土台のPane
    private TabPane tabp;
    private Tab tab1, tab2;
    private ToolBar tb1 = new ToolBar();  //ap0の下にあるtoolbar
    private Button[] bttb1 = new Button[2]; //toolbarにあるbutton
    private Image[] img = new Image[3];    // 2-ap0のimage 0-Homeのimage 1-終了のimage
    private DropShadow dr;
    private Button[][] btgenso = new Button[18][7];
    private Label[] lbicon = new Label[6]; 
    private GridPane gp = new GridPane();  
    //クイズのため
    int tensu = 0; //
    private ChoiceBox cb1;
    private TextField[] tf1 = new TextField[3];
    private Label[] lbk = new Label[7];
    private RadioButton rb1, rb2, rb3;
    private ToggleGroup tgb;
    private Button btsaiten;
    private TextArea ta;
    
    
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        //
        //　画面の構成の構成は，別の図解資料も見ながら確認してください． 
        //
        //
        
        // 大元の土台に貼り付けるタイトルラベルの作成とAnchoPane上での配置位置指定．
        lb = new Label("＜＜　プログラミング演習Ｂ　プロジェクト土台　＞＞");
        lb.setLayoutX(250);// AnchorPane ap0条での配置位置を指定する
        lb.setLayoutY(8); 
        lb.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        lb.setFont(Font.font(null ,FontWeight.BOLD, 25));
        lb.setTextFill(Color.RED);
        
        dr = new DropShadow();
        dr.setRadius(5.0);
        dr.setOffsetX(7.0);
        dr.setOffsetY(3.0);
        dr.setColor(Color.color(1.0, 0.5, 0.5));
        lb.setEffect(dr);
        
        //
        // 大元の土台のPaneの作成
        img[2] = new Image(getClass().getResourceAsStream("images/hoa_hoc.jpg"));
        ap0 = new AnchorPane();
        ap0.setBackground(new Background(new BackgroundImage(img[2], BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT )));
        //
        //
        //　中央に配置するBorderPaneの作成
        //　　　開始画面，クイズA，クイズBを貼り付ける相手
        bp = new BorderPane();
        //最初のimage
        bp.setPrefSize(900, 470);
        bp.setLayoutX(80);
        bp.setLayoutY(50);
        //tabPane
        tabp = new TabPane();
        tabp.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
        tabp.setPrefSize(850, 440);
        tabp.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        tab1 = new Tab("元素の周期表");
        tab2 = new Tab();
        
        //////////////////////////////////////////////////////////////////////////

        //toolbar
        tb1.setLayoutX(0);  
        tb1.setLayoutY(550);
        tb1.setPrefSize(1024, 30);
        tb1.setBackground(new Background (new BackgroundFill(Color.LIGHTGRAY, null, null)));
        
        //toolbarの上にあるimage
        img[0] = new Image(getClass().getResourceAsStream("images/kagakuicon.png"));
        img[1] = new Image(getClass().getResourceAsStream("images/closeicon.jpg"));
        
        for(int i = 0; i < 2; i++){
            bttb1[i] = new Button();
            bttb1[i].setOnAction(new ButtonEventHandlerTB1());
            bttb1[i].setGraphic(new ImageView(img[i]));
            tb1.getItems().add(bttb1[i]);
        }
        //////////////////////////////////////////////////////////////////////////
        //AnchorPaneであるap0内で位置決めされている
        ap0.getChildren().addAll(lb,bp,tb1);
       
        //元素の周期表   
        gp.setHgap(2);
        gp.setVgap(2);
        gp.setPrefSize(50, 50);
               
        btgenso[0][0] = new Button("H"); 
        gp.add(btgenso[0][0], 0, 0);
        btgenso[0][0].setPrefSize(45, 60);
        btgenso[0][0].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        btgenso[0][0].setOnAction(new ButtonGensoEventHandler());
        btgenso[0][0].addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseEventHandler());
        btgenso[0][0].addEventHandler(MouseEvent.MOUSE_EXITED, new MouseEventHandler());
        //アルカリ金属 (1-6)
        lbicon[0] = new Label("アルカリ金属");
        lbicon[0].setBackground(new Background(new BackgroundFill(Color.DODGERBLUE, null, null)));
        btgenso[0][1] = new Button("Li");
        btgenso[0][2] = new Button("Na");
        btgenso[0][3] = new Button("K");
        btgenso[0][4] = new Button("Rb");
        btgenso[0][5] = new Button("Cs");
        btgenso[0][6] = new Button("Fr");
        for (int i = 0; i < 1; i++){
            for (int j = 1; j <7; j++ ){
                btgenso[i][j].setBackground(new Background(new BackgroundFill(Color.DODGERBLUE, null, null)));
            }
        }
        
        //アルカリ土類金属
        lbicon[1] = new Label("アルカリ土類金属");
        lbicon[1].setBackground(new Background(new BackgroundFill(Color.GOLDENROD, null, null)));
        //be, Mg はアルカリ土類金属ではない
        btgenso[1][1] = new Button("Be");
        btgenso[1][2] = new Button("Mg");
        btgenso[1][1].setBackground(new Background(new BackgroundFill(Color.ORCHID, null, null)));
        btgenso[1][2].setBackground(new Background(new BackgroundFill(Color.ORCHID, null, null)));
        btgenso[1][3] = new Button("Ca");
        btgenso[1][4] = new Button("Sr");
        btgenso[1][5] = new Button("Ba");
        btgenso[1][6] = new Button("Ra");
        for (int i = 0; i < 2; i++){
            for (int j = 1; j <7; j++ ){
                gp.add(btgenso[i][j], i, j);
                btgenso[i][j].setPrefSize(45, 60);
                btgenso[i][j].setOnAction(new ButtonGensoEventHandler());
                btgenso[i][j].addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseEventHandler());
                btgenso[i][j].addEventHandler(MouseEvent.MOUSE_EXITED, new MouseEventHandler());
                
            }
        }
        for (int i = 1; i < 2; i++){
            for (int j = 1; j <7; j++ ){
                btgenso[i][j].setBackground(new Background(new BackgroundFill(Color.GOLDENROD, null, null)));
            }
        }
        //他の金属
        lbicon[2] = new Label("他の金属");
        lbicon[2].setBackground(new Background(new BackgroundFill(Color.ORCHID, null, null)));
        btgenso[2][3] = new Button("Sc");
        btgenso[2][4] = new Button("Y");
        btgenso[2][5] = new Button();
        btgenso[2][6] = new Button();
        btgenso[3][3] = new Button("Ti");
        btgenso[3][4] = new Button("Zr");
        btgenso[3][5] = new Button("Hf");
        btgenso[3][6] = new Button("Rf");
        btgenso[4][3] = new Button("V");
        btgenso[4][4] = new Button("Nb");
        btgenso[4][5] = new Button("Ta");
        btgenso[4][6] = new Button("Db");
        btgenso[5][3] = new Button("Cr");
        btgenso[5][4] = new Button("Mo");
        btgenso[5][5] = new Button("W");
        btgenso[5][6] = new Button("Sg");
        btgenso[6][3] = new Button("Mn");
        btgenso[6][4] = new Button("Tc");
        btgenso[6][5] = new Button("Re");
        btgenso[6][6] = new Button("Bh");
        btgenso[7][3] = new Button("Fe");
        btgenso[7][4] = new Button("Ru");
        btgenso[7][5] = new Button("Os");
        btgenso[7][6] = new Button("Hs");
        btgenso[8][3] = new Button("Co");
        btgenso[8][4] = new Button("Rh");
        btgenso[8][5] = new Button("Ir");
        btgenso[8][6] = new Button("Mt");
        btgenso[9][3] = new Button("Ni");
        btgenso[9][4] = new Button("Pd");
        btgenso[9][5] = new Button("Pt");
        btgenso[9][6] = new Button();
        btgenso[10][3] = new Button("Cu");
        btgenso[10][4] = new Button("Ag");
        btgenso[10][5] = new Button("Au");
        btgenso[10][6] = new Button();
        btgenso[11][3] = new Button("Zn");
        btgenso[11][4] = new Button("Cd");
        btgenso[11][5] = new Button("Hg");
        btgenso[11][6] = new Button();
        for (int i = 2; i < 12; i++){
            for (int j = 3; j <7; j++ ){
                gp.add(btgenso[i][j], i, j);
                btgenso[i][j].setPrefSize(45, 60);
                btgenso[i][j].setOnAction(new ButtonGensoEventHandler());
                btgenso[i][j].addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseEventHandler());
                btgenso[i][j].addEventHandler(MouseEvent.MOUSE_EXITED, new MouseEventHandler());
                btgenso[i][j].setBackground(new Background(new BackgroundFill(Color.ORCHID, null, null)));
                
            }
        }
        btgenso[12][2] = new Button("Al");
        btgenso[12][3] = new Button("Ga");
        btgenso[12][4] = new Button("In");
        btgenso[12][5] = new Button("Tl");
        for (int j = 2; j<6 ; j++ ){
            btgenso[12][j].setBackground(new Background(new BackgroundFill(Color.ORCHID, null, null)));
        }
        btgenso[13][3] = new Button("Ge");
        btgenso[13][4] = new Button("Sn");
        btgenso[13][5] = new Button("Pb");
        for (int j = 3; j<6 ; j++ ){
            btgenso[13][j].setBackground(new Background(new BackgroundFill(Color.ORCHID, null, null)));
        }
        btgenso[14][4] = new Button("Sb");
        btgenso[14][5] = new Button("Bl");
        for (int j = 4; j<6 ; j++ ){
            btgenso[14][j].setBackground(new Background(new BackgroundFill(Color.ORCHID, null, null)));
        }
        btgenso[15][5] = new Button("Po");
        btgenso[15][5].setBackground(new Background(new BackgroundFill(Color.ORCHID, null, null)));
        
        
        //他の非金属 
        lbicon[3] = new Label("他の非金属");
        lbicon[3].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        btgenso[12][1] = new Button("B");
        btgenso[12][1].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        btgenso[13][1] = new Button("C");
        btgenso[13][2] = new Button("Si");
        btgenso[13][1].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        btgenso[13][2].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        btgenso[14][1] = new Button("N");
        btgenso[14][2] = new Button("P");
        btgenso[14][3] = new Button("As");
        for (int j = 1; j<4 ; j++ ){
            btgenso[14][j].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        }
        btgenso[15][1] = new Button("O");
        btgenso[15][2] = new Button("S");
        btgenso[15][3] = new Button("Se");
        btgenso[15][4] = new Button("Te");
        for (int j = 1; j<5 ; j++ ){
            btgenso[15][j].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        }
        //ハロゲン
        lbicon[4] = new Label("ハロゲン");
        lbicon[4].setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, null, null)));
        btgenso[16][1] = new Button("F");
        btgenso[16][2] = new Button("Cl");
        btgenso[16][3] = new Button("Br");
        btgenso[16][4] = new Button("I");
        btgenso[16][5] = new Button("At");
        for (int j = 1; j<6 ; j++ ){
            btgenso[16][j].setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, null, null)));
        }
        for (int i = 12; i < 17; i++){
            for (int j = 1; j <6; j++ ){
                gp.add(btgenso[i][j], i, j);
                btgenso[i][j].setPrefSize(45, 60);
                btgenso[i][j].setOnAction(new ButtonGensoEventHandler());
                btgenso[i][j].addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseEventHandler());
                btgenso[i][j].addEventHandler(MouseEvent.MOUSE_EXITED, new MouseEventHandler());
            }
        }
        //希ガス
        lbicon[5] = new Label("希ガス");
        lbicon[5].setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, null, null)));
        btgenso[17][0] = new Button("He");
        btgenso[17][1] = new Button("Ne");
        btgenso[17][2] = new Button("Ar");
        btgenso[17][3] = new Button("Kr");
        btgenso[17][4] = new Button("Xe");
        btgenso[17][5] = new Button("Rn");
        for (int i = 17; i < 18; i++){
            for (int j = 0; j <6; j++ ){
                gp.add(btgenso[i][j], i, j);
                btgenso[i][j].setPrefSize(45, 60);
                btgenso[i][j].setOnAction(new ButtonGensoEventHandler());
                btgenso[i][j].addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseEventHandler());
                btgenso[i][j].addEventHandler(MouseEvent.MOUSE_EXITED, new MouseEventHandler());
                btgenso[i][j].setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, null, null))); 
            }
        }
        
        btgenso[5][0] = new Button("元");
        btgenso[6][0] = new Button("素");
        btgenso[7][0] = new Button("周");
        btgenso[8][0] = new Button("期");
        btgenso[9][0] = new Button("表");
        for(int i = 5; i < 10; i++){
            btgenso[i][0].setFont(Font.font(17));
            btgenso[i][0].setPrefSize(40, 40);
            gp.add(btgenso[i][0], i, 0);
            btgenso[i][0].setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null))); 
        }
        
        btgenso[7][1] = new Button();
        btgenso[7][1].setFont(Font.font("arial black" , 18));
        btgenso[7][1].setTextFill(Color.RED);
        btgenso[7][1].setPrefSize(50, 60);
        gp.add(btgenso[7][1], 7, 1);
        btgenso[7][1].setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null)));
        
        tab1.setContent(gp);
        tabp.getTabs().add(tab1);
        //正解
        btsaiten = new Button("正解");
        btsaiten.setOnAction(new ButtonGensoEventHandler());
        
        
        ///////////////////////

        //シーンの作成 (額縁の土台) 
        Scene sc = new Scene(ap0, 1024, 600);
        //ステージへの追加（額縁へ）
        stage.setScene(sc);
        //ステージの表示（額縁のタイトル，表示指示）
        stage.setTitle("My Graphics　with JavaFX");
        stage.show();
    }
   ////////toolbarにあるbuttonを押すイベント
    class ButtonEventHandlerTB1 implements EventHandler<ActionEvent> {

        public void handle(ActionEvent e) {
            Button tb = (Button) e.getSource();
            if (tb == bttb1[0]) {
                for(int i = 0; i < 6; i++){
                    lbicon[i].setFont(Font.font(20));
                    tb1.getItems().add(lbicon[i]);
                }
                bp.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                bp.setCenter(tabp); 
                
            } 
            else if (tb == bttb1[1]){
                Alert alt1 = new Alert(Alert.AlertType.CONFIRMATION);
                alt1.setTitle("終了");
                alt1.getDialogPane().setHeaderText("本当に終了しますか!");
                Optional<ButtonType> res = alt1.showAndWait();
                if(res.get() == ButtonType.OK){
                    System.exit(0);
                }
            }
        }
    }
    
    class MouseEventHandler implements EventHandler<MouseEvent>
   {
      public void handle(MouseEvent e)
      {
         Button tb = (Button) e.getSource();
         if(e.getEventType() == MouseEvent.MOUSE_ENTERED){
             btgenso[7][1].setText(tb.getText());
         }
         else if(e.getEventType() == MouseEvent.MOUSE_EXITED){
         }
      }
   }
    class ButtonGensoEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent e) {
             Button tb = (Button) e.getSource();

                    //質問の回答
             tf1[0] = new TextField();
             tf1[1] = new TextField();
             tf1[2] = new TextField();
             for(int i = 0; i <3; i++){
                 tf1[i].setOnAction(new ButtonGensoEventHandler());
             }
             cb1 = new ChoiceBox();
             cb1.getItems().addAll(" 気体 "," 液体 "," 固体 ");
             cb1.setOnAction(new ButtonGensoEventHandler());
             rb1 = new RadioButton(" 金属 ");
             rb2 = new RadioButton(" 非金属 ");
             rb3 = new RadioButton(" 希ガス ");
             tgb = new ToggleGroup();
             rb1.setToggleGroup(tgb);
             rb2.setToggleGroup(tgb);
             rb3.setToggleGroup(tgb);
             rb1.setOnAction(new ButtonGensoEventHandler());
             rb2.setOnAction(new ButtonGensoEventHandler());
             rb3.setOnAction(new ButtonGensoEventHandler());

             ///////// 2
             lbk[0] = new Label("問 1: " + tb.getText() + " は何の記号？   ");
             HBox hb2 = new HBox();
             hb2.getChildren().addAll(lbk[0], tf1[0]);
             //////// 1
             HBox hb1 = new HBox();
             lbk[1] = new Label("問 2: " + tb.getText() + " の物質の状態は   ");
             hb1.getChildren().addAll(lbk[1], cb1);
             ///////// 3
             lbk[2] = new Label("問 3: " + tb.getText() + "  どんなグループですか。 ");
             HBox hb3 = new HBox();
             hb3.getChildren().addAll(lbk[2],rb1, rb2, rb3);
             //////// 4
             lbk[3] = new Label("問 4: " + tb.getText() + " の原子量は  ");
             HBox hb4 = new HBox();
             hb4.getChildren().addAll(lbk[3], tf1[1]);
             /////// 5
             lbk[4] = new Label("問 5: " + tb.getText() + " の原子番号は ");
             HBox hb5 = new HBox();
             hb5.getChildren().addAll(lbk[4], tf1[2]);
             ///////////////////////////////////
             for (int i = 0 ; i < 5; i++){
                 lbk[i].setFont(Font.font(null, 15));
             }
             VBox vb1 = new VBox();
             vb1.setPadding(new Insets(10, 10, 10, 10));
             vb1.setSpacing(15);
             vb1.getChildren().addAll(hb2, hb1, hb3, hb4, hb5, btsaiten);
             
             if (tb.getText() != "正解"){
                tab2 = new Tab(tb.getText());
                tab2.setContent(vb1);
                tabp.getTabs().add(tab2);
             }
             else{
                 tab2 = new Tab("正解");
                 ta = new TextArea(" 水素\n "+"気体\n"+"非金属\n"+"1\n"+"1\n");
                 tab2.setContent(ta);
                tabp.getTabs().add(tab2);
             }
        }
        
    }
        
    }
