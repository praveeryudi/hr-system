package com.example.demo.util;

public class MaintenanceUtil {

    private MaintenanceUtil(){}

    public static String getMonthInString(int input) {
        String month = null;
        switch(input) {
            case 1:
                month = "JAN";
                break;
            case 2:
                month = "FEB";
                break;
            case 3:
                month = "MAR";
                break;
            case 4:
                month = "APR";
                break;
            case 5:
                month = "MAY";
                break;
            case 6:
                month = "JUN";
                break;
            case 7:
                month = "JUL";
                break;
            case 8:
                month = "AUG";
                break;
            case 9:
                month = "SEP";
                break;
            case 10:
                month = "OCT";
                break;
            case 11:
                month = "NOV";
                break;
            case 12:
                month = "DEC";
                break;
        }
        return month;
    }

    public static String[] getPreviousMonthYear(String currentMonth, String currentYear) {
        String previousMonth;
        String previousYear = currentYear;
        switch(currentMonth) {
            case "JAN":
                previousMonth = "DEC";
                previousYear = String.valueOf(Integer.valueOf(currentYear) - 1);
                break;
            case "FEB":
                previousMonth = "JAN";
                break;
            case "MAR":
                previousMonth = "FEB";
                break;
            case "APR":
                previousMonth = "MAR";
                break;
            case "MAY":
                previousMonth = "APR";
                break;
            case "JUN":
                previousMonth = "MAY";
                break;
            case "JUL":
                previousMonth = "JUN";
                break;
            case "AUG":
                previousMonth = "JUL";
                break;
            case "SEP":
                previousMonth = "AUG";
                break;
            case "OCT":
                previousMonth = "SEP";
                break;
            case "NOV":
                previousMonth = "OCT";
                break;
            case "DEC":
                previousMonth = "NOV";
                break;
            default:
                previousMonth = currentMonth;
                break;
        }
        return new String[] {previousMonth, previousYear};
    }

    public static String get6MonthsQuery() {
        return "SELECT monthname(CURDATE() - INTERVAL 6 MONTH) as Month, year(CURDATE() - INTERVAL 6 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 5 MONTH) as Month, year(CURDATE() - INTERVAL 5 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 4 MONTH) as Month, year(CURDATE() - INTERVAL 4 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 3 MONTH) as Month, year(CURDATE() - INTERVAL 3 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 2 MONTH) as Month, year(CURDATE() - INTERVAL 2 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 1 MONTH) as Month, year(CURDATE() - interval 1 month ) as Year";
    }

    public static String get3MonthsQuery() {
        return "SELECT monthname(CURDATE() - INTERVAL 3 MONTH) as Month, year(CURDATE() - INTERVAL 3 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 2 MONTH) as Month, year(CURDATE() - INTERVAL 2 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 1 MONTH) as Month, year(CURDATE() - INTERVAL 1 MONTH) as Year";
    }

    public static String get9MonthsQuery() {
        return "SELECT monthname(CURDATE() - INTERVAL 9 MONTH) as Month, year(CURDATE() - INTERVAL 9 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 8 MONTH) as Month, year(CURDATE() - INTERVAL 8 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 7 MONTH) as Month, year(CURDATE() - INTERVAL 7 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 6 MONTH) as Month, year(CURDATE() - INTERVAL 6 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 5 MONTH) as Month, year(CURDATE() - INTERVAL 5 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 4 MONTH) as Month, year(CURDATE() - INTERVAL 4 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 3 MONTH) as Month, year(CURDATE() - INTERVAL 3 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 2 MONTH) as Month, year(CURDATE() - INTERVAL 2 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 1 MONTH) as Month, year(CURDATE() - interval 1 month ) as Year";
    }

    public static String get12MonthsQuery() {
        return "SELECT monthname(CURDATE() - INTERVAL 12 MONTH) as Month, year(CURDATE() - INTERVAL 12 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 11 MONTH) as Month, year(CURDATE() - INTERVAL 11 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 10 MONTH) as Month, year(CURDATE() - INTERVAL 10 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 9 MONTH) as Month, year(CURDATE() - INTERVAL 9 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 8 MONTH) as Month, year(CURDATE() - INTERVAL 8 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 7 MONTH) as Month, year(CURDATE() - INTERVAL 7 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 6 MONTH) as Month, year(CURDATE() - INTERVAL 6 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 5 MONTH) as Month, year(CURDATE() - INTERVAL 5 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 4 MONTH) as Month, year(CURDATE() - INTERVAL 4 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 3 MONTH) as Month, year(CURDATE() - INTERVAL 3 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 2 MONTH) as Month, year(CURDATE() - INTERVAL 2 MONTH) as Year\n" +
                "UNION\n" +
                "SELECT monthname(CURDATE() - INTERVAL 1 MONTH) as Month, year(CURDATE() - interval 1 month ) as Year";
    }
}
