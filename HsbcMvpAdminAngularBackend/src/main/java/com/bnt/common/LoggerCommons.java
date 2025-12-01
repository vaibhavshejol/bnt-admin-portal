package com.bnt.common;
/**************************
 * @author nilofar.shaikh *
 **************************/

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.renovite.ripps.ap.common.util.JPAUtils;
import com.renovite.ripps.ap.rest.entity.BaseEntity;

public class LoggerCommons {

    private LoggerCommons() { }

    private static Logger userActivityLogger = LogManager.getLogger("userActivityLogger");

    public static void logUserActivity(Object entity, String eventType, String clientIP) {

        Class<?> classType = entity.getClass();
        String tableName = JPAUtils.getTableName(classType);

        if (tablename != null) {
            BaseEntity baseEntity = (BaseEntity) entity;

            userActivityLogger.info("******************{}{}", eventType,
                         " operation has been invoked with below details:**********************");
            userActivityLogger.info(" Event Originator IP: {}", clientIP);
            logEventDetails(baseEntity, classType, tableName);
            if (JPAUtils.isEventPostInsertOrPostUpdate(eventType)) {
                logAuditDetails(baseEntity, eventType);
            }
            userActivityLogger.info("************************************************************************");
        }
    }

    private static void logAuditDetails(BaseEntity baseEntity,String eventType) {
        userActivityLogger.info(" Created by User Id : {}", baseEntity.getCreatedBy());
        userActivityLogger.info(" Created On : {}", baseEntity.getCreatedOn());

        if(JPAUtils.POST_UPDATE_EVENT.equals(eventType)) {
            userActivityLogger.info(" Updated By User Id : {}", baseEntity.getUpdatedBy());
            userActivityLogger.info(" Updated On : {}", baseEntity.getUpdatedOn());
        }
    }

    private static void logEventDetails(BaseEntity baseEntity, Class<?> classType, String tableName) {

        userActivityLogger.info("Entity name : '{}{}{}{}" , classType.getSimpleName(), "' And Table name : '", tableName, "'");
        userActivityLogger.info("System Generated Entity Id : {}", baseEntity.getId());
    }
}
