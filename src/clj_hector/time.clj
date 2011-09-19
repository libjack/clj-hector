(ns clj-hector.time
  (:import [me.prettyprint.cassandra.utils TimeUUIDUtils]
           [java.util Date]
           [org.joda.time Instant]))

(defn uuid-now
  []
  (TimeUUIDUtils/getUniqueTimeUUIDinMillis))

(defprotocol ToEpoch
  (epoch [_] "Returns the milliseconds since epoch"))

(extend-protocol ToEpoch
  Date (epoch [d] (.getTime d))
  Instant (epoch [i] (.getMillis i)))

(defn uuid
  "Creates a UUID from an epoch value"
  [time]
  (TimeUUIDUtils/getTimeUUID time))

;; (defn uuid
;;   [date]
;;   (TimeUUIDUtils/getTimeUUID (.getTime date)))

(defn to-bytes
  [uuid]
  (TimeUUIDUtils/asByteArray uuid))

(defn from-bytes
  [bytes]
  (TimeUUIDUtils/toUUID bytes))

(defn get-date
  "Retrieves the Date represented by the bytes of a TimeUUID instance."
  [bytes]
  (java.util.Date. (TimeUUIDUtils/getTimeFromUUID bytes)))