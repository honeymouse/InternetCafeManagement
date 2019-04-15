package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Test extends JFrame {

    private JPanel contentPane;
    private final JPanel panel = new JPanel();

    private String clientId = null;
    private String oneTimePrice = null;
    private Date starDate = null;

    private long useSecond = 0;//요금계산위해 초값입력하여사용
    protected long addSecond = 0;// 추가시간 초단위
    protected long addSecondMoney = 0;//1시간당 금액으로 초단위 금액을 추출하는데사용
    private String useMoney = "0";
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    public Test(String clientId, String oneTimePrice, Date starDate) {
        this.clientId = clientId;
        this.oneTimePrice = oneTimePrice;
        this.starDate = starDate;

        try {
            this.addSecondMoney = 100;//요금상승기본단위
            //요금상승기본단위에 해당하는 1시간당 나누기 요금/단위 = 100원당 초 생성
            this.addSecond = ((60*60)/(Integer.parseInt(this.oneTimePrice)/this.addSecondMoney));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //pc사용하고 시간지나면 시간만큼 요금증가 시키는 메소드
    public void addMoney() {
        int useMoney = (int)((this.useSecond / this.addSecond)* this.addSecondMoney);
    }
    //사용시간 계산하는 메소드
    private String useTime(Date udate) {//시작시간입력
        Calendar c2 = Calendar.getInstance(); //시간차 계산하기위한 달력객체
        c2.setTime(new java.util.Date()); //현재시간셋팅

        long intervalMilli = (c2.getTimeInMillis() - udate.getTime())/1000; //초값나옴
        long minute = 60; //초값을 분으로 계산하기위한 값
        long hour = minute * 60; //초값을 시간으로 계산하기 위한값

        int useHour = (int)(intervalMilli/hour);
        int useMinute = (int)((intervalMilli%hour)/minute);
        int useSecond = (int)((intervalMilli%hour)%minute);
        this.useSecond = intervalMilli;//요금계산을 위해 멤버변수에 초값을 입력사용

        String tmp = (useHour <10) ? "0"+useHour : useHour+"";
        tmp += ":";
        tmp += (useMinute <10) ? "0"+useMinute : useMinute+"";
        tmp +=":";
        tmp += (useSecond <10) ? "0"+useSecond : useSecond+"";
        return tmp;
    }
    //스레드동작
    public void run() {
        textField.setText(this.clientId + "님 안녕하세요.");
        while(true) {
            addMoney();
            textField_1.setText(this.useMoney+"원");
            textField_2.setText(useTime(this.starDate));
            System.out.println(this.starDate);
            try {
                Thread.sleep(1000);//밀리세컨드만큼 쉬고
            }catch(Exception e) {e.printStackTrace();}
        }

    }


    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        new Test();
    }

    /**
     * Create the frame.
     */
    public Test() {
        setTitle("\uC0AC\uC6A9\uC790");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        getContentPane().add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("사용자");
        lblNewLabel.setBounds(14, 49, 62, 18);
        contentPane.add(lblNewLabel);

        JLabel label = new JLabel("요금");
        label.setBounds(14, 102, 62, 18);
        contentPane.add(label);

        JLabel label_1 = new JLabel("시간");
        label_1.setBounds(14, 151, 62, 18);
        contentPane.add(label_1);

        JButton btnNewButton = new JButton("사용중지");
        btnNewButton.setBounds(251, 114, 105, 59);
        contentPane.add(btnNewButton);
        //사용자이름
        textField = new JTextField();
        textField.setBounds(70, 46, 116, 24);
        contentPane.add(textField);
        textField.setText(this.clientId+"님입니다.");
        textField.setColumns(10);
        //사용시간당 요금
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(70, 99, 116, 24);
        textField_1.setText(this.useMoney+"원");
        contentPane.add(textField_1);

        //사용시간
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(70, 148, 116, 24);
        contentPane.add(textField_2);

        //pc번호 이건 뭐...아직할지말지고민
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(240, 46, 116, 24);
        contentPane.add(textField_3);

        setVisible(true);
    }
}