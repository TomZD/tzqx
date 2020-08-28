package cn.movinginfo.tztf.sem.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SevReleaseChannelInstanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SevReleaseChannelInstanceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(Long value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(Long value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(Long value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(Long value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(Long value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(Long value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<Long> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<Long> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(Long value1, Long value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(Long value1, Long value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("valid is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("valid is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(String value) {
            addCriterion("valid =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(String value) {
            addCriterion("valid <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(String value) {
            addCriterion("valid >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(String value) {
            addCriterion("valid >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(String value) {
            addCriterion("valid <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(String value) {
            addCriterion("valid <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLike(String value) {
            addCriterion("valid like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotLike(String value) {
            addCriterion("valid not like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<String> values) {
            addCriterion("valid in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<String> values) {
            addCriterion("valid not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(String value1, String value2) {
            addCriterion("valid between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(String value1, String value2) {
            addCriterion("valid not between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdatorIsNull() {
            addCriterion("updator is null");
            return (Criteria) this;
        }

        public Criteria andUpdatorIsNotNull() {
            addCriterion("updator is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatorEqualTo(Long value) {
            addCriterion("updator =", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotEqualTo(Long value) {
            addCriterion("updator <>", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorGreaterThan(Long value) {
            addCriterion("updator >", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorGreaterThanOrEqualTo(Long value) {
            addCriterion("updator >=", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorLessThan(Long value) {
            addCriterion("updator <", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorLessThanOrEqualTo(Long value) {
            addCriterion("updator <=", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorIn(List<Long> values) {
            addCriterion("updator in", values, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotIn(List<Long> values) {
            addCriterion("updator not in", values, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorBetween(Long value1, Long value2) {
            addCriterion("updator between", value1, value2, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotBetween(Long value1, Long value2) {
            addCriterion("updator not between", value1, value2, "updator");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andChannelStateIsNull() {
            addCriterion("channel_state is null");
            return (Criteria) this;
        }

        public Criteria andChannelStateIsNotNull() {
            addCriterion("channel_state is not null");
            return (Criteria) this;
        }

        public Criteria andChannelStateEqualTo(Byte value) {
            addCriterion("channel_state =", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateNotEqualTo(Byte value) {
            addCriterion("channel_state <>", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateGreaterThan(Byte value) {
            addCriterion("channel_state >", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("channel_state >=", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateLessThan(Byte value) {
            addCriterion("channel_state <", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateLessThanOrEqualTo(Byte value) {
            addCriterion("channel_state <=", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateIn(List<Byte> values) {
            addCriterion("channel_state in", values, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateNotIn(List<Byte> values) {
            addCriterion("channel_state not in", values, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateBetween(Byte value1, Byte value2) {
            addCriterion("channel_state between", value1, value2, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateNotBetween(Byte value1, Byte value2) {
            addCriterion("channel_state not between", value1, value2, "channelState");
            return (Criteria) this;
        }

        public Criteria andRelaseTimeIsNull() {
            addCriterion("relase_time is null");
            return (Criteria) this;
        }

        public Criteria andRelaseTimeIsNotNull() {
            addCriterion("relase_time is not null");
            return (Criteria) this;
        }

        public Criteria andRelaseTimeEqualTo(Date value) {
            addCriterion("relase_time =", value, "relaseTime");
            return (Criteria) this;
        }

        public Criteria andRelaseTimeNotEqualTo(Date value) {
            addCriterion("relase_time <>", value, "relaseTime");
            return (Criteria) this;
        }

        public Criteria andRelaseTimeGreaterThan(Date value) {
            addCriterion("relase_time >", value, "relaseTime");
            return (Criteria) this;
        }

        public Criteria andRelaseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("relase_time >=", value, "relaseTime");
            return (Criteria) this;
        }

        public Criteria andRelaseTimeLessThan(Date value) {
            addCriterion("relase_time <", value, "relaseTime");
            return (Criteria) this;
        }

        public Criteria andRelaseTimeLessThanOrEqualTo(Date value) {
            addCriterion("relase_time <=", value, "relaseTime");
            return (Criteria) this;
        }

        public Criteria andRelaseTimeIn(List<Date> values) {
            addCriterion("relase_time in", values, "relaseTime");
            return (Criteria) this;
        }

        public Criteria andRelaseTimeNotIn(List<Date> values) {
            addCriterion("relase_time not in", values, "relaseTime");
            return (Criteria) this;
        }

        public Criteria andRelaseTimeBetween(Date value1, Date value2) {
            addCriterion("relase_time between", value1, value2, "relaseTime");
            return (Criteria) this;
        }

        public Criteria andRelaseTimeNotBetween(Date value1, Date value2) {
            addCriterion("relase_time not between", value1, value2, "relaseTime");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNull() {
            addCriterion("channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(Long value) {
            addCriterion("channel_id =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(Long value) {
            addCriterion("channel_id <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(Long value) {
            addCriterion("channel_id >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("channel_id >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(Long value) {
            addCriterion("channel_id <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("channel_id <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<Long> values) {
            addCriterion("channel_id in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<Long> values) {
            addCriterion("channel_id not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(Long value1, Long value2) {
            addCriterion("channel_id between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("channel_id not between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andSenderAlarmIdIsNull() {
            addCriterion("sender_alarm_id is null");
            return (Criteria) this;
        }

        public Criteria andSenderAlarmIdIsNotNull() {
            addCriterion("sender_alarm_id is not null");
            return (Criteria) this;
        }

        public Criteria andSenderAlarmIdEqualTo(Long value) {
            addCriterion("sender_alarm_id =", value, "senderAlarmId");
            return (Criteria) this;
        }

        public Criteria andSenderAlarmIdNotEqualTo(Long value) {
            addCriterion("sender_alarm_id <>", value, "senderAlarmId");
            return (Criteria) this;
        }

        public Criteria andSenderAlarmIdGreaterThan(Long value) {
            addCriterion("sender_alarm_id >", value, "senderAlarmId");
            return (Criteria) this;
        }

        public Criteria andSenderAlarmIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sender_alarm_id >=", value, "senderAlarmId");
            return (Criteria) this;
        }

        public Criteria andSenderAlarmIdLessThan(Long value) {
            addCriterion("sender_alarm_id <", value, "senderAlarmId");
            return (Criteria) this;
        }

        public Criteria andSenderAlarmIdLessThanOrEqualTo(Long value) {
            addCriterion("sender_alarm_id <=", value, "senderAlarmId");
            return (Criteria) this;
        }

        public Criteria andSenderAlarmIdIn(List<Long> values) {
            addCriterion("sender_alarm_id in", values, "senderAlarmId");
            return (Criteria) this;
        }

        public Criteria andSenderAlarmIdNotIn(List<Long> values) {
            addCriterion("sender_alarm_id not in", values, "senderAlarmId");
            return (Criteria) this;
        }

        public Criteria andSenderAlarmIdBetween(Long value1, Long value2) {
            addCriterion("sender_alarm_id between", value1, value2, "senderAlarmId");
            return (Criteria) this;
        }

        public Criteria andSenderAlarmIdNotBetween(Long value1, Long value2) {
            addCriterion("sender_alarm_id not between", value1, value2, "senderAlarmId");
            return (Criteria) this;
        }

        public Criteria andSenderNoticeIdIsNull() {
            addCriterion("sender_notice_id is null");
            return (Criteria) this;
        }

        public Criteria andSenderNoticeIdIsNotNull() {
            addCriterion("sender_notice_id is not null");
            return (Criteria) this;
        }

        public Criteria andSenderNoticeIdEqualTo(Long value) {
            addCriterion("sender_notice_id =", value, "senderNoticeId");
            return (Criteria) this;
        }

        public Criteria andSenderNoticeIdNotEqualTo(Long value) {
            addCriterion("sender_notice_id <>", value, "senderNoticeId");
            return (Criteria) this;
        }

        public Criteria andSenderNoticeIdGreaterThan(Long value) {
            addCriterion("sender_notice_id >", value, "senderNoticeId");
            return (Criteria) this;
        }

        public Criteria andSenderNoticeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sender_notice_id >=", value, "senderNoticeId");
            return (Criteria) this;
        }

        public Criteria andSenderNoticeIdLessThan(Long value) {
            addCriterion("sender_notice_id <", value, "senderNoticeId");
            return (Criteria) this;
        }

        public Criteria andSenderNoticeIdLessThanOrEqualTo(Long value) {
            addCriterion("sender_notice_id <=", value, "senderNoticeId");
            return (Criteria) this;
        }

        public Criteria andSenderNoticeIdIn(List<Long> values) {
            addCriterion("sender_notice_id in", values, "senderNoticeId");
            return (Criteria) this;
        }

        public Criteria andSenderNoticeIdNotIn(List<Long> values) {
            addCriterion("sender_notice_id not in", values, "senderNoticeId");
            return (Criteria) this;
        }

        public Criteria andSenderNoticeIdBetween(Long value1, Long value2) {
            addCriterion("sender_notice_id between", value1, value2, "senderNoticeId");
            return (Criteria) this;
        }

        public Criteria andSenderNoticeIdNotBetween(Long value1, Long value2) {
            addCriterion("sender_notice_id not between", value1, value2, "senderNoticeId");
            return (Criteria) this;
        }

        public Criteria andSenderEventIdIsNull() {
            addCriterion("sender_event_id is null");
            return (Criteria) this;
        }

        public Criteria andSenderEventIdIsNotNull() {
            addCriterion("sender_event_id is not null");
            return (Criteria) this;
        }

        public Criteria andSenderEventIdEqualTo(Long value) {
            addCriterion("sender_event_id =", value, "senderEventId");
            return (Criteria) this;
        }

        public Criteria andSenderEventIdNotEqualTo(Long value) {
            addCriterion("sender_event_id <>", value, "senderEventId");
            return (Criteria) this;
        }

        public Criteria andSenderEventIdGreaterThan(Long value) {
            addCriterion("sender_event_id >", value, "senderEventId");
            return (Criteria) this;
        }

        public Criteria andSenderEventIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sender_event_id >=", value, "senderEventId");
            return (Criteria) this;
        }

        public Criteria andSenderEventIdLessThan(Long value) {
            addCriterion("sender_event_id <", value, "senderEventId");
            return (Criteria) this;
        }

        public Criteria andSenderEventIdLessThanOrEqualTo(Long value) {
            addCriterion("sender_event_id <=", value, "senderEventId");
            return (Criteria) this;
        }

        public Criteria andSenderEventIdIn(List<Long> values) {
            addCriterion("sender_event_id in", values, "senderEventId");
            return (Criteria) this;
        }

        public Criteria andSenderEventIdNotIn(List<Long> values) {
            addCriterion("sender_event_id not in", values, "senderEventId");
            return (Criteria) this;
        }

        public Criteria andSenderEventIdBetween(Long value1, Long value2) {
            addCriterion("sender_event_id between", value1, value2, "senderEventId");
            return (Criteria) this;
        }

        public Criteria andSenderEventIdNotBetween(Long value1, Long value2) {
            addCriterion("sender_event_id not between", value1, value2, "senderEventId");
            return (Criteria) this;
        }

        public Criteria andArriveTimeIsNull() {
            addCriterion("arrive_time is null");
            return (Criteria) this;
        }

        public Criteria andArriveTimeIsNotNull() {
            addCriterion("arrive_time is not null");
            return (Criteria) this;
        }

        public Criteria andArriveTimeEqualTo(Date value) {
            addCriterion("arrive_time =", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeNotEqualTo(Date value) {
            addCriterion("arrive_time <>", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeGreaterThan(Date value) {
            addCriterion("arrive_time >", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("arrive_time >=", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeLessThan(Date value) {
            addCriterion("arrive_time <", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeLessThanOrEqualTo(Date value) {
            addCriterion("arrive_time <=", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeIn(List<Date> values) {
            addCriterion("arrive_time in", values, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeNotIn(List<Date> values) {
            addCriterion("arrive_time not in", values, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeBetween(Date value1, Date value2) {
            addCriterion("arrive_time between", value1, value2, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeNotBetween(Date value1, Date value2) {
            addCriterion("arrive_time not between", value1, value2, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageIsNull() {
            addCriterion("feed_back_message is null");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageIsNotNull() {
            addCriterion("feed_back_message is not null");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageEqualTo(String value) {
            addCriterion("feed_back_message =", value, "feedBackMessage");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageNotEqualTo(String value) {
            addCriterion("feed_back_message <>", value, "feedBackMessage");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageGreaterThan(String value) {
            addCriterion("feed_back_message >", value, "feedBackMessage");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageGreaterThanOrEqualTo(String value) {
            addCriterion("feed_back_message >=", value, "feedBackMessage");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageLessThan(String value) {
            addCriterion("feed_back_message <", value, "feedBackMessage");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageLessThanOrEqualTo(String value) {
            addCriterion("feed_back_message <=", value, "feedBackMessage");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageLike(String value) {
            addCriterion("feed_back_message like", value, "feedBackMessage");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageNotLike(String value) {
            addCriterion("feed_back_message not like", value, "feedBackMessage");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageIn(List<String> values) {
            addCriterion("feed_back_message in", values, "feedBackMessage");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageNotIn(List<String> values) {
            addCriterion("feed_back_message not in", values, "feedBackMessage");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageBetween(String value1, String value2) {
            addCriterion("feed_back_message between", value1, value2, "feedBackMessage");
            return (Criteria) this;
        }

        public Criteria andFeedBackMessageNotBetween(String value1, String value2) {
            addCriterion("feed_back_message not between", value1, value2, "feedBackMessage");
            return (Criteria) this;
        }

        public Criteria andDataIsNull() {
            addCriterion("data is null");
            return (Criteria) this;
        }

        public Criteria andDataIsNotNull() {
            addCriterion("data is not null");
            return (Criteria) this;
        }

        public Criteria andDataEqualTo(String value) {
            addCriterion("data =", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotEqualTo(String value) {
            addCriterion("data <>", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThan(String value) {
            addCriterion("data >", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualTo(String value) {
            addCriterion("data >=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThan(String value) {
            addCriterion("data <", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualTo(String value) {
            addCriterion("data <=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLike(String value) {
            addCriterion("data like", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotLike(String value) {
            addCriterion("data not like", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataIn(List<String> values) {
            addCriterion("data in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotIn(List<String> values) {
            addCriterion("data not in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataBetween(String value1, String value2) {
            addCriterion("data between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotBetween(String value1, String value2) {
            addCriterion("data not between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andRemoveContentIsNull() {
            addCriterion("remove_content is null");
            return (Criteria) this;
        }

        public Criteria andRemoveContentIsNotNull() {
            addCriterion("remove_content is not null");
            return (Criteria) this;
        }

        public Criteria andRemoveContentEqualTo(String value) {
            addCriterion("remove_content =", value, "removeContent");
            return (Criteria) this;
        }

        public Criteria andRemoveContentNotEqualTo(String value) {
            addCriterion("remove_content <>", value, "removeContent");
            return (Criteria) this;
        }

        public Criteria andRemoveContentGreaterThan(String value) {
            addCriterion("remove_content >", value, "removeContent");
            return (Criteria) this;
        }

        public Criteria andRemoveContentGreaterThanOrEqualTo(String value) {
            addCriterion("remove_content >=", value, "removeContent");
            return (Criteria) this;
        }

        public Criteria andRemoveContentLessThan(String value) {
            addCriterion("remove_content <", value, "removeContent");
            return (Criteria) this;
        }

        public Criteria andRemoveContentLessThanOrEqualTo(String value) {
            addCriterion("remove_content <=", value, "removeContent");
            return (Criteria) this;
        }

        public Criteria andRemoveContentLike(String value) {
            addCriterion("remove_content like", value, "removeContent");
            return (Criteria) this;
        }

        public Criteria andRemoveContentNotLike(String value) {
            addCriterion("remove_content not like", value, "removeContent");
            return (Criteria) this;
        }

        public Criteria andRemoveContentIn(List<String> values) {
            addCriterion("remove_content in", values, "removeContent");
            return (Criteria) this;
        }

        public Criteria andRemoveContentNotIn(List<String> values) {
            addCriterion("remove_content not in", values, "removeContent");
            return (Criteria) this;
        }

        public Criteria andRemoveContentBetween(String value1, String value2) {
            addCriterion("remove_content between", value1, value2, "removeContent");
            return (Criteria) this;
        }

        public Criteria andRemoveContentNotBetween(String value1, String value2) {
            addCriterion("remove_content not between", value1, value2, "removeContent");
            return (Criteria) this;
        }

        public Criteria andRemoveReleaseTimeIsNull() {
            addCriterion("remove_release_time is null");
            return (Criteria) this;
        }

        public Criteria andRemoveReleaseTimeIsNotNull() {
            addCriterion("remove_release_time is not null");
            return (Criteria) this;
        }

        public Criteria andRemoveReleaseTimeEqualTo(Date value) {
            addCriterion("remove_release_time =", value, "removeReleaseTime");
            return (Criteria) this;
        }

        public Criteria andRemoveReleaseTimeNotEqualTo(Date value) {
            addCriterion("remove_release_time <>", value, "removeReleaseTime");
            return (Criteria) this;
        }

        public Criteria andRemoveReleaseTimeGreaterThan(Date value) {
            addCriterion("remove_release_time >", value, "removeReleaseTime");
            return (Criteria) this;
        }

        public Criteria andRemoveReleaseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("remove_release_time >=", value, "removeReleaseTime");
            return (Criteria) this;
        }

        public Criteria andRemoveReleaseTimeLessThan(Date value) {
            addCriterion("remove_release_time <", value, "removeReleaseTime");
            return (Criteria) this;
        }

        public Criteria andRemoveReleaseTimeLessThanOrEqualTo(Date value) {
            addCriterion("remove_release_time <=", value, "removeReleaseTime");
            return (Criteria) this;
        }

        public Criteria andRemoveReleaseTimeIn(List<Date> values) {
            addCriterion("remove_release_time in", values, "removeReleaseTime");
            return (Criteria) this;
        }

        public Criteria andRemoveReleaseTimeNotIn(List<Date> values) {
            addCriterion("remove_release_time not in", values, "removeReleaseTime");
            return (Criteria) this;
        }

        public Criteria andRemoveReleaseTimeBetween(Date value1, Date value2) {
            addCriterion("remove_release_time between", value1, value2, "removeReleaseTime");
            return (Criteria) this;
        }

        public Criteria andRemoveReleaseTimeNotBetween(Date value1, Date value2) {
            addCriterion("remove_release_time not between", value1, value2, "removeReleaseTime");
            return (Criteria) this;
        }

        public Criteria andRemoveArriveTimeIsNull() {
            addCriterion("remove_arrive_time is null");
            return (Criteria) this;
        }

        public Criteria andRemoveArriveTimeIsNotNull() {
            addCriterion("remove_arrive_time is not null");
            return (Criteria) this;
        }

        public Criteria andRemoveArriveTimeEqualTo(Date value) {
            addCriterion("remove_arrive_time =", value, "removeArriveTime");
            return (Criteria) this;
        }

        public Criteria andRemoveArriveTimeNotEqualTo(Date value) {
            addCriterion("remove_arrive_time <>", value, "removeArriveTime");
            return (Criteria) this;
        }

        public Criteria andRemoveArriveTimeGreaterThan(Date value) {
            addCriterion("remove_arrive_time >", value, "removeArriveTime");
            return (Criteria) this;
        }

        public Criteria andRemoveArriveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("remove_arrive_time >=", value, "removeArriveTime");
            return (Criteria) this;
        }

        public Criteria andRemoveArriveTimeLessThan(Date value) {
            addCriterion("remove_arrive_time <", value, "removeArriveTime");
            return (Criteria) this;
        }

        public Criteria andRemoveArriveTimeLessThanOrEqualTo(Date value) {
            addCriterion("remove_arrive_time <=", value, "removeArriveTime");
            return (Criteria) this;
        }

        public Criteria andRemoveArriveTimeIn(List<Date> values) {
            addCriterion("remove_arrive_time in", values, "removeArriveTime");
            return (Criteria) this;
        }

        public Criteria andRemoveArriveTimeNotIn(List<Date> values) {
            addCriterion("remove_arrive_time not in", values, "removeArriveTime");
            return (Criteria) this;
        }

        public Criteria andRemoveArriveTimeBetween(Date value1, Date value2) {
            addCriterion("remove_arrive_time between", value1, value2, "removeArriveTime");
            return (Criteria) this;
        }

        public Criteria andRemoveArriveTimeNotBetween(Date value1, Date value2) {
            addCriterion("remove_arrive_time not between", value1, value2, "removeArriveTime");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageIsNull() {
            addCriterion("remove_feed_back_message is null");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageIsNotNull() {
            addCriterion("remove_feed_back_message is not null");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageEqualTo(String value) {
            addCriterion("remove_feed_back_message =", value, "removeFeedBackMessage");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageNotEqualTo(String value) {
            addCriterion("remove_feed_back_message <>", value, "removeFeedBackMessage");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageGreaterThan(String value) {
            addCriterion("remove_feed_back_message >", value, "removeFeedBackMessage");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageGreaterThanOrEqualTo(String value) {
            addCriterion("remove_feed_back_message >=", value, "removeFeedBackMessage");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageLessThan(String value) {
            addCriterion("remove_feed_back_message <", value, "removeFeedBackMessage");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageLessThanOrEqualTo(String value) {
            addCriterion("remove_feed_back_message <=", value, "removeFeedBackMessage");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageLike(String value) {
            addCriterion("remove_feed_back_message like", value, "removeFeedBackMessage");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageNotLike(String value) {
            addCriterion("remove_feed_back_message not like", value, "removeFeedBackMessage");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageIn(List<String> values) {
            addCriterion("remove_feed_back_message in", values, "removeFeedBackMessage");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageNotIn(List<String> values) {
            addCriterion("remove_feed_back_message not in", values, "removeFeedBackMessage");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageBetween(String value1, String value2) {
            addCriterion("remove_feed_back_message between", value1, value2, "removeFeedBackMessage");
            return (Criteria) this;
        }

        public Criteria andRemoveFeedBackMessageNotBetween(String value1, String value2) {
            addCriterion("remove_feed_back_message not between", value1, value2, "removeFeedBackMessage");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeIsNull() {
            addCriterion("use_default_remove_mode is null");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeIsNotNull() {
            addCriterion("use_default_remove_mode is not null");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeEqualTo(String value) {
            addCriterion("use_default_remove_mode =", value, "useDefaultRemoveMode");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeNotEqualTo(String value) {
            addCriterion("use_default_remove_mode <>", value, "useDefaultRemoveMode");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeGreaterThan(String value) {
            addCriterion("use_default_remove_mode >", value, "useDefaultRemoveMode");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeGreaterThanOrEqualTo(String value) {
            addCriterion("use_default_remove_mode >=", value, "useDefaultRemoveMode");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeLessThan(String value) {
            addCriterion("use_default_remove_mode <", value, "useDefaultRemoveMode");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeLessThanOrEqualTo(String value) {
            addCriterion("use_default_remove_mode <=", value, "useDefaultRemoveMode");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeLike(String value) {
            addCriterion("use_default_remove_mode like", value, "useDefaultRemoveMode");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeNotLike(String value) {
            addCriterion("use_default_remove_mode not like", value, "useDefaultRemoveMode");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeIn(List<String> values) {
            addCriterion("use_default_remove_mode in", values, "useDefaultRemoveMode");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeNotIn(List<String> values) {
            addCriterion("use_default_remove_mode not in", values, "useDefaultRemoveMode");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeBetween(String value1, String value2) {
            addCriterion("use_default_remove_mode between", value1, value2, "useDefaultRemoveMode");
            return (Criteria) this;
        }

        public Criteria andUseDefaultRemoveModeNotBetween(String value1, String value2) {
            addCriterion("use_default_remove_mode not between", value1, value2, "useDefaultRemoveMode");
            return (Criteria) this;
        }

        public Criteria andSendPurposeIsNull() {
            addCriterion("send_purpose is null");
            return (Criteria) this;
        }

        public Criteria andSendPurposeIsNotNull() {
            addCriterion("send_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andSendPurposeEqualTo(String value) {
            addCriterion("send_purpose =", value, "sendPurpose");
            return (Criteria) this;
        }

        public Criteria andSendPurposeNotEqualTo(String value) {
            addCriterion("send_purpose <>", value, "sendPurpose");
            return (Criteria) this;
        }

        public Criteria andSendPurposeGreaterThan(String value) {
            addCriterion("send_purpose >", value, "sendPurpose");
            return (Criteria) this;
        }

        public Criteria andSendPurposeGreaterThanOrEqualTo(String value) {
            addCriterion("send_purpose >=", value, "sendPurpose");
            return (Criteria) this;
        }

        public Criteria andSendPurposeLessThan(String value) {
            addCriterion("send_purpose <", value, "sendPurpose");
            return (Criteria) this;
        }

        public Criteria andSendPurposeLessThanOrEqualTo(String value) {
            addCriterion("send_purpose <=", value, "sendPurpose");
            return (Criteria) this;
        }

        public Criteria andSendPurposeLike(String value) {
            addCriterion("send_purpose like", value, "sendPurpose");
            return (Criteria) this;
        }

        public Criteria andSendPurposeNotLike(String value) {
            addCriterion("send_purpose not like", value, "sendPurpose");
            return (Criteria) this;
        }

        public Criteria andSendPurposeIn(List<String> values) {
            addCriterion("send_purpose in", values, "sendPurpose");
            return (Criteria) this;
        }

        public Criteria andSendPurposeNotIn(List<String> values) {
            addCriterion("send_purpose not in", values, "sendPurpose");
            return (Criteria) this;
        }

        public Criteria andSendPurposeBetween(String value1, String value2) {
            addCriterion("send_purpose between", value1, value2, "sendPurpose");
            return (Criteria) this;
        }

        public Criteria andSendPurposeNotBetween(String value1, String value2) {
            addCriterion("send_purpose not between", value1, value2, "sendPurpose");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andSenderTypeIsNull() {
            addCriterion("sender_type is null");
            return (Criteria) this;
        }

        public Criteria andSenderTypeIsNotNull() {
            addCriterion("sender_type is not null");
            return (Criteria) this;
        }

        public Criteria andSenderTypeEqualTo(String value) {
            addCriterion("sender_type =", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeNotEqualTo(String value) {
            addCriterion("sender_type <>", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeGreaterThan(String value) {
            addCriterion("sender_type >", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sender_type >=", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeLessThan(String value) {
            addCriterion("sender_type <", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeLessThanOrEqualTo(String value) {
            addCriterion("sender_type <=", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeLike(String value) {
            addCriterion("sender_type like", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeNotLike(String value) {
            addCriterion("sender_type not like", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeIn(List<String> values) {
            addCriterion("sender_type in", values, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeNotIn(List<String> values) {
            addCriterion("sender_type not in", values, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeBetween(String value1, String value2) {
            addCriterion("sender_type between", value1, value2, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeNotBetween(String value1, String value2) {
            addCriterion("sender_type not between", value1, value2, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderNumberIsNull() {
            addCriterion("sender_number is null");
            return (Criteria) this;
        }

        public Criteria andSenderNumberIsNotNull() {
            addCriterion("sender_number is not null");
            return (Criteria) this;
        }

        public Criteria andSenderNumberEqualTo(String value) {
            addCriterion("sender_number =", value, "senderNumber");
            return (Criteria) this;
        }

        public Criteria andSenderNumberNotEqualTo(String value) {
            addCriterion("sender_number <>", value, "senderNumber");
            return (Criteria) this;
        }

        public Criteria andSenderNumberGreaterThan(String value) {
            addCriterion("sender_number >", value, "senderNumber");
            return (Criteria) this;
        }

        public Criteria andSenderNumberGreaterThanOrEqualTo(String value) {
            addCriterion("sender_number >=", value, "senderNumber");
            return (Criteria) this;
        }

        public Criteria andSenderNumberLessThan(String value) {
            addCriterion("sender_number <", value, "senderNumber");
            return (Criteria) this;
        }

        public Criteria andSenderNumberLessThanOrEqualTo(String value) {
            addCriterion("sender_number <=", value, "senderNumber");
            return (Criteria) this;
        }

        public Criteria andSenderNumberLike(String value) {
            addCriterion("sender_number like", value, "senderNumber");
            return (Criteria) this;
        }

        public Criteria andSenderNumberNotLike(String value) {
            addCriterion("sender_number not like", value, "senderNumber");
            return (Criteria) this;
        }

        public Criteria andSenderNumberIn(List<String> values) {
            addCriterion("sender_number in", values, "senderNumber");
            return (Criteria) this;
        }

        public Criteria andSenderNumberNotIn(List<String> values) {
            addCriterion("sender_number not in", values, "senderNumber");
            return (Criteria) this;
        }

        public Criteria andSenderNumberBetween(String value1, String value2) {
            addCriterion("sender_number between", value1, value2, "senderNumber");
            return (Criteria) this;
        }

        public Criteria andSenderNumberNotBetween(String value1, String value2) {
            addCriterion("sender_number not between", value1, value2, "senderNumber");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}