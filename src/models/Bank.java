package models;

import ds.BranchesByKDTree;

public class Bank {
    private String name;
    public BranchesByKDTree branches; // branches.root.right is the main Branch

    // TrieTree fields
    public Bank[] children = new Bank[Statics.SMALL_ALPHABET_SIZE];

    public Bank(String name, BankBranch mainBranch) {
        this.name = name;
        this.branches = new BranchesByKDTree();
        BankBranch branch = copyValue(mainBranch);
        this.branches.insert(branch);
        for (int i = 0; i < children.length; i++) {
            children[i] = null;
        }
    }

    private BankBranch copyValue(BankBranch branch) {
        BankBranch copy = new BankBranch(branch.getName(), branch.getBankName(), branch.getLocation());
        return copy;
    }

    // TrieTree null bank data constructor
    public Bank() {
        this.name = null;
        this.branches = null;
        for (int i = 0; i < children.length; i++) {
            children[i] = null;
        }
    }

    public String getName() {
        return name;
    }

    public BankBranch getMainBranch() {
        return this.branches.getRoot().right;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printAllData() {
        System.out.println("--------" + this.name + "--------");
        BranchesByKDTree.printInorder(getMainBranch());
        System.out.println("------------------------");
    }

    public void addBranch(BankBranch branch) {
        BankBranch copy = copyValue(branch);
        this.branches.insert(copy);
    }

    public boolean isEqualWith(Bank bank) {
        return getMainBranch().getLocation() == bank.getMainBranch().getLocation();
    }

    public static boolean nameChecker(String name) {

        if (name.length() > 20) {
            System.out.println("Name is long, Choose shorter name!");
            return false;
        }

        if (name.length() == 0) {
            System.out.println("Name should have one character at least!");
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) < 'a' || name.charAt(i) > 'z') {
                System.out.println("Name should be contains just a to z!");
                return false;
            }
        }

        return true;
    }
}
