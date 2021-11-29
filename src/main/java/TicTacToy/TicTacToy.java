package TicTacToy;

import java.util.Random;
import java.util.Scanner;
//1)Разобрался с кодом, но подглядывая в методичку и в запись урока и перелопатил много информации;
//2) Сделал метод проверки победы;
class ticTacToe {
    public static int SIZE = 3;
    public static int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }


    public static boolean checkWin(char symb) {

        boolean diagonalRight = true;
        boolean diagonalLeft = true;
        boolean verticals;
        boolean horizons;
        {
            for (int i = 1; i < DOTS_TO_WIN; i++) {
                diagonalRight &= (map[i][i] == symb);
                diagonalLeft &= (map[3 - i - 1][i] == symb);

            }
            if (diagonalRight || diagonalLeft) return true;
        }
        {


            for (int vertical = 0; vertical < DOTS_TO_WIN; vertical++) {
                verticals = true;
                horizons = true;
                for (int horizon = 0; horizon < DOTS_TO_WIN; horizon++) {
                    horizons &= (map[horizon][vertical] == symb);
                    verticals &= (map[vertical][horizon] == symb);
                }
                if (horizons || verticals) return true;


            }
        }
        return false;
    }


    public static boolean isMapFull() {//метод проверяет заполнины ли поля
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

        public static void aiTurn () {
        int x, y;
        do {
            x = sc.nextInt()+1;
            y = sc.nextInt()+1;
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }



    public static void humanTurn () {//метод вызывает создает вызов консоли для пользоватиля
            int x, y;
            do {
                System.out.println("Введите координаты в формате X Y");
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            } while (!isCellValid(x, y)); 
            map[y][x] = DOT_X;
        }


        public static boolean isCellValid ( int x, int y){//метод создает границы карты
            if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
            if (map[y][x] == DOT_EMPTY) return true;
            return false;
        }

        public static void initMap () {//метод создает картку
            map = new char[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

        public static void printMap () {//метод отпечатывает карту
            for (int i = 0; i <= SIZE; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 0; i < SIZE; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();


        }
    }




