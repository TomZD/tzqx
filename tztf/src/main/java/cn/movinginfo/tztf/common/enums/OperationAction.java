package cn.movinginfo.tztf.common.enums;


public enum OperationAction {
    Alert,Update,Cancel,Upgrade, Downgrade, KeepOn;
    
    public static String toString(Integer action){
        
        OperationAction actionEnum = OperationAction.values()[action];
        switch (actionEnum) {
            case Alert:
                return "Alert";
            case Update:
                return "Update";
            case Cancel:
                return "Cancel";
            case Upgrade:
                return "Upgrade";
            case Downgrade:
                return "Downgrade";
            case KeepOn:
                return "KeepOn";
            default:
                break;
        }
        
        return "Alert";
    }
    
    public static String getMessageType(Integer action){
        OperationAction actionEnum = OperationAction.values()[action];
        switch (actionEnum) {
            case Alert:
                return "Alert";
            case Update:
                return "Update";
            case Cancel:
                return "Cancel";
            case Upgrade:
                return "Update";
            case Downgrade:
                return "Update";
            case KeepOn:
                return "Update";
            default:
                break;
        }
        
        return "Alert";
    }
    
    public static String toCnString(Integer action){
        
        OperationAction actionEnum = OperationAction.values()[action];
        switch (actionEnum) {
            case Alert:
                return "发布";
            case Update:
                return "变更";
            case Cancel:
                return "解除";
            case Upgrade:
                return "升级";
            case Downgrade:
                return "降级";
            case KeepOn:
                return "继续发布";
        }
        
        //默认值为alert
        return "发布";
    }
    
//    public static boolean isLocked(int state){
//        return state == Locked_Upate.ordinal() || state == Locked_Cancel.ordinal() || state == Locked_Alert.ordinal()
//                || state == Locked_Upgrade.ordinal() || state == Locked_Downgrade.ordinal()
//                || state == Locked_KeepOn.ordinal();
//    }
//    
//    public static int getLockState(int lastOp){
//        
//        OperationAction actionEnum = OperationAction.values()[lastOp];
//        
//        switch (actionEnum) {
//            case Alert:
//                return Locked_Alert.ordinal();
//            case Update:
//                return Locked_Upate.ordinal();
//            case Cancel:
//                return Locked_Cancel.ordinal();
//            case Upgrade:
//                return Locked_Upgrade.ordinal();
//            case Downgrade:
//                return Locked_Downgrade.ordinal();
//            case KeepOn:
//                return Locked_KeepOn.ordinal();
//            default:
//                return actionEnum.ordinal();
//        }
//    }
//    
//    public static int getUnLockState(int lastOp){
//        
//        OperationAction actionEnum = OperationAction.values()[lastOp];
//        
//        switch (actionEnum) {
//            case Locked_Alert:
//                return Alert.ordinal();
//            case Locked_Upate:
//                return Update.ordinal();
//            case Locked_Cancel:
//                return Cancel.ordinal();
//            case Locked_Downgrade:
//                return Downgrade.ordinal();
//            case Locked_Upgrade:
//                return Upgrade.ordinal();
//            case Locked_KeepOn:
//                return KeepOn.ordinal();
//            default:
//                return actionEnum.ordinal();
//        }
//    }
}
