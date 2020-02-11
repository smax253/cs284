import original.*;
import test.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HW2TestScript {
    static int points = 0;
    static Random r = new Random();
    static Scanner s = new Scanner(System.in);
    static ArrayList<Integer> insertedValues = new ArrayList<>();
    static StringBuilder errors = new StringBuilder();
    public static int SLLinsert(test.SingleLinkedList target, original.SingleLinkedList orig){
        try {
            boolean success = true;
            for (int i = 0; i < 30; i++) {
                int insert = r.nextInt(1500);
                insertedValues.add(insert);
                success = success && target.insert(insert);
                orig.insert(insert);
            }
            if (success) return 4;
            errors.append("SLLInsert failed (-4)\n");
            return 0;
        }
        catch(Exception e){
            errors.append("SLLInsert crashed (-4)\n");
            return 0;
        }
    }
    public static int SLLfind(test.SingleLinkedList target){
        try{
            for(int i = 0; i<15; i++){
                int index = r.nextInt(insertedValues.size());
                if(!target.find(insertedValues.get(index))) {
                    errors.append("SLLFind failed (-4)\n");
                    return 0;
                }
            }
            return 4;
        }catch(Exception e){
            errors.append("SLLFind crashed (-4)\n");
            return 0;
        }
    }
    public static int SLLdelete(test.SingleLinkedList target, original.SingleLinkedList orig){
        try{
            boolean success = true;
            for(int i = 0; i<5; i++){
                int index = r.nextInt(insertedValues.size());
                int delete = insertedValues.get(index);
                if(!target.delete(delete)) {
                    errors.append("SLLdelete failed (-4)\n");
                    return 0;
                }
                orig.delete(delete);
                int result = insertedValues.remove(index);
            }
            return 4;
        }catch(Exception e){
            errors.append("SLLdelete crashed (-4)\n");

            return 0;
        }
    }
    public static int SLLprint(test.SingleLinkedList target, original.SingleLinkedList orig){
        try{
            System.out.print("Original: ");
            orig.print();
            System.out.print("Target  : ");
            target.print();

            System.out.println("Press 1 to accept, 3 to reject, and 2 for partial");
            int result = s.nextInt();
            if (result == 1) return 4;
            else if (result == 2) {
                errors.append("Partial credit on SLL print (-2)\n");
                return 2;
            }
            else {
                errors.append("Incorrect output for SLL print (-4)\n");
                return 0;
            }

        }catch(Exception e){
            errors.append("SLLprint crashed (-4)\n");

            return 0;
        }
    }
    public static int skipInsert(test.SkipList target, original.SkipList orig){
        insertedValues = new ArrayList<>();
        try {
            boolean success = true;
            for (int i = 0; i < 100; i++) {
                int insert = r.nextInt(3000);
                insertedValues.add(insert);
                success = success && target.insert(insert);
                orig.insert(insert);
            }
            if (success) return 10;
            errors.append("SkipInsert failed (-10)\n");

            return 0;
        }
        catch(Exception e){
            errors.append("SkipInsert crashed (-10)\n");

            return 0;
        }
    }
    public static int skipFind(test.SkipList target){
        try{
            for(int i = 0; i<15; i++){
                int index = r.nextInt(insertedValues.size());
                if(!target.find(insertedValues.get(index))) {
                    errors.append("SkipFind failed (-10)\n");
                    return 0;
                }
            }
            return 10;
        }catch(Exception e){
            errors.append("SkipFind crashed (-10)\n");

            return 0;
        }
    }
    public static int skipDelete(test.SkipList target, original.SkipList orig){
        try{
            boolean success = true;
            for(int i = 0; i<5; i++){
                int index = r.nextInt(insertedValues.size());
                int delete = insertedValues.get(index);
                if(!target.delete(delete)) {
                    errors.append("SkipDelete failed (-10)\n");
                    return 0;
                }
                orig.delete(delete);
                int result = insertedValues.remove(index);
            }
            return 10;
        }catch(Exception e){
            errors.append("SkipDelete crashed (-10)\n");

            return 0;
        }
    }
    public static int skipPrint(test.SkipList target, original.SkipList orig) {
        try {
            System.out.print("Original: ");
            orig.print(2);
            orig.print(5);
            orig.print(7);
            System.out.print("Target  : ");
            target.print(2);
            target.print(5);
            target.print(7);

            System.out.println("Press 1 to accept, 3 to reject, and 2 for partial");
            int result = s.nextInt();
            if (result == 1) {
                return 10;
            }
            else if (result == 2) {
                errors.append("SkipList print partial credit (-5)\n");
                return 5;
            }
            else {
                errors.append("SkipList print incorrect output (-10)\n");
                return 0;
            }

        } catch (Exception e) {
            errors.append("SkipList print crashed (-10)\n");
            return 0;
        }
    }
    public static int listComparison(){
        int totalpoints = 0;
        try {
            test.ListComparison.main(null);
            System.out.println("\nPress 1 to accept, 3 to reject, and 2 for partial:" +
                    "\nCompares time of insertion");
            switch(s.nextInt()){
                case 1:
                    totalpoints += 5;
                    break;
                case 2:
                    errors.append("Partial credit for insert time comparison (-2.5)\n");
                    totalpoints += 2.5;
                    break;
                default:
                    errors.append("Incorrect or missing output for insert time comparison (-5)\n");
                    break;
            }
            System.out.println("Compares SLL and SkipList level 0");
            switch(s.nextInt()){
                case 1:
                    totalpoints += 5;
                    break;
                case 2:
                    errors.append("Partial credit for comparing list output (-2.5)\n");

                    totalpoints += 2.5;
                    break;
                default:
                    errors.append("Incorrect or missing list output comparison (-5)\n");

                    break;
            }
            System.out.println("Compares time of find");
            switch(s.nextInt()){
                case 1:
                    totalpoints += 10;
                    break;
                case 2:
                    errors.append("Partial credit for find time comparison (-5)\n");

                    totalpoints += 5;
                    break;
                default:
                    errors.append("Incorrect or missing output for find time comparison (-10)\n");

                    break;
            }
            System.out.println("Compares time of deletion");
            switch(s.nextInt()){
                case 1:
                    totalpoints += 10;
                    break;
                case 2:
                    errors.append("Partial credit for delete time comparison (-5)\n");

                    totalpoints += 5;
                    break;
                default:
                    errors.append("Incorrect or missing output for delete time comparison (-10)\n");

                    break;
            }
        }
        catch(Exception e){
            errors.append("List Comparison crashed (-30)\n");
            return 0;
        }
        return totalpoints;
    }
    public static void main(String[] args){
        try {
            test.SingleLinkedList targetSLL = new test.SingleLinkedList();
            points += 4;
            original.SingleLinkedList origSLL = new original.SingleLinkedList();
            System.out.println("Testing SLL: ");
            points += SLLinsert(targetSLL, origSLL);
            points += SLLfind(targetSLL);
            points += SLLdelete(targetSLL, origSLL);
            points += SLLprint(targetSLL, origSLL);
        }
        catch (Exception e){
            errors.append("Constructor for SLL failed (-20)\n");
        }
        try {
            test.SkipList targetSkip = new test.SkipList();
            original.SkipList origSkip = new original.SkipList();
            points += 10;
            points += skipInsert(targetSkip, origSkip);
            points += skipFind(targetSkip);
            points += skipDelete(targetSkip, origSkip);
            points += skipPrint(targetSkip, origSkip);
        }
        catch(Exception e){
            errors.append("Constructor for SkipList failed (-50)\n");
        }
        try{
            points += listComparison();
        }
        catch(Exception e){
            errors.append("Comparison crashed (-30)\n");
        }
        System.err.println("\n"+errors.toString());
        System.err.println("Total points: "+points);

    }
}
