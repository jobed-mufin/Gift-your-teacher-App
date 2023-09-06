package com.decagon.rewardyourteacherapi.enums;

public enum MessageType {
    FUNDED_WALLET("You've funded your wallet with N"),
    TEACHER_WITHDRAW("Your withdrawal of N%s was successful"),
    REWARD_TEACHER("You've sent money to "),
    TEACHER_REWARDED("A former student has successfully funded your wallet. Say Hi...");

    private final String str;
    MessageType(String str) {
        this.str = str;
    }

    public String getStr(){
        return  str;
    }


}
