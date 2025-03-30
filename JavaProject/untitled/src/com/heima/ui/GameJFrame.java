package com.heima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener ,ActionListener{
    int[][] data = new int[4][4];
    int x = 0;
    int y = 0;
    int count = 0;
    int[][] victory = {
            {1, 2, 3,4},
            {5, 6, 7,8},
            {9, 10, 11,12},
            {13, 14, 15,0},
    };

    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");

    public GameJFrame() {

        initJFrame();

        initJMenuBar();
        //初始化数据
        initData();
        initImage();
        this.addKeyListener(this);
        System.out.println(isWin());

        this.setVisible(true);

    }


    private void initData() {
        int[] temparr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //随机生成索引
        Random random = new Random();
        //交换位置
        for (int i = 0; i < temparr.length; i++) {
            int index = random.nextInt(16);
            int temp = temparr[index];
            temparr[index] = temparr[i];
            temparr[i] = temp;

        }
        //遍历二维数组
        for (int i = 0; i < 16; i++) {
            data[i / 4][i % 4] = temparr[i];
        }
        //获取空白位置

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j] == 0) {
                    x = i;
                    y = j;
                    System.out.println(x + "," + y);
                    break;
                }
            }
        }


    }

    private void initImage() {

        this.getContentPane().removeAll();
        if (isWin()) {
            JButton end = new JButton("游戏成功");
            end.setBounds(350, 50, 100, 50);
            this.getContentPane().add(end);
        }
        JLabel Step = new JLabel("步数：" + count);
        Step.setBounds(50, 50, 100, 50);
        this.getContentPane().add(Step);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ImageIcon Icon = new ImageIcon("/Users/renhaokuang/Desktop/JavaProject/image/image" + data[i][j] + ".jpg");
                JLabel label = new JLabel(Icon);
                label.setBounds(160 * j + 83, 160 * i + 134, 160, 160);
                label.setBorder(new BevelBorder(0));
                this.getContentPane().add(label);
            }


        }

        this.getContentPane().repaint();


    }


    //初始化菜单
    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu function = new JMenu("功能");
        JMenu about = new JMenu("关于我们");

        function.add(replayItem);
        function.add(reLoginItem);
        function.add(closeItem);
        about.add(accountItem);

        jMenuBar.add(function);
        jMenuBar.add(about);
        this.setJMenuBar(jMenuBar);

        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

    }

    //初始化界面
    private void initJFrame() {
        this.setSize(800, 850);
        this.setTitle("拼图单机版 v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);


    }


    @Override
    public void keyTyped(KeyEvent e) {

        System.out.println("按下了按键");


    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == 87) {
            this.getContentPane().removeAll();

            JLabel label = new JLabel(new ImageIcon("/Users/renhaokuang/Desktop/JavaProject/image/all.jpg"));
            label.setBounds(83, 134, 640, 640);
            label.setBorder(new BevelBorder(BevelBorder.RAISED));
            this.getContentPane().add(label);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == 81) {
            int[][] victory = {
                    {1, 2, 3,4},
                    {5, 6, 7,8},
                    {9, 10, 11,12},
                    {13, 14, 15,0},
            };
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    data[i][j] = victory[i][j];
                }
            }

        }
        initImage();

        int code = e.getKeyCode();
//        System.out.println(code);
        if (code == 87) {
            initImage();
        }
        if (code == 38) {
            if (isWin()) {
                return;
            }
//            System.out.println("按了上");
            if (x == 3) {
                return;
            }

            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;

            x++;
            count++;
            initImage();
        } else if (code == 40) {
            if (isWin()) {
                return;
            }
//            System.out.println("按了下");
            if (x == 0) {
                return;
            }

            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            count++;

            initImage();

        } else if (code == 37) {
            if (isWin()) {
                return;
            }
//            System.out.println("按了左");
            if (y == 3) {
                return;
            }

            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            count++;

            initImage();

        } else if (code == 39) {
            if (isWin()) {
                return;
            }
//            System.out.println("按了右");
            if (y == 0) {
                return;
            }

            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            count++;

            initImage();

        }


    }

    public boolean isWin() {
        for (int i = 0; i < victory.length; i++) {
            for (int j = 0; j < victory[i].length; j++) {
                if (data[i][j] != victory[i][j]) {
                    return false;
                }
            }
        }
        return true;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == replayItem) {
            System.out.println("重新游戏");
            count = 0;
            initData();
            initImage();

        }else if (source == reLoginItem) {
            System.out.println("重新登陆");
            this.setVisible(false);
            new LoginJFrame();

        }else if (source == closeItem){
            System.exit(0);
        }else if (source == accountItem){
            System.out.println("公众号");
            JDialog jDialog = new JDialog();
            JLabel label = new JLabel(new ImageIcon("/Users/renhaokuang/Desktop/JavaProject/image/all.jpg"));
            label.setBounds(0,0,200,200);
            jDialog.getContentPane().add(label);
            jDialog.setSize(200,200);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }


    }
}
