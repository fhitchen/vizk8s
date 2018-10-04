(ns vizk8s.model
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [vizk8s.communication :as comm]))

;; define your app data so that it doesn't get over-written on reload

(defonce app-state
  (atom
   {:namespaces [:com-att-cwdigital :com-att-cwdigital-catalog :com-att-cwdigital-checkout
                 :com-att-cwdigital-partner :com-att-cwdigital-shop]
    :text "Hello world!"
    :nodes []
    :deployments []
    :replicatsets []
    :pods []}))

(defn refresh-nodes [app-state]
  (go
    (let [nodes (:items (:body (<! (comm/get-nodes))))]
      (println "refreshing nodes")
      (swap! app-state update-in [:nodes] replace nodes))))

(defn refresh-deployments [app-state]
  (go
    (let [deployments (:items (:body (<! (comm/get-deployments))))]
      (println "refreshing deployments")
      (swap! app-state update-in [:deployments] replace deployments))))

(defn refresh-replicasets [app-state]
  (go
    (let [replicasets (:items (:body (<! (comm/get-replicasets))))]
      (println "refreshing replicasets")
      (swap! app-state update-in [:replicasets] replace replicasets))))

(defn refresh-pods [app-state]
  (go
    (let [pods (:items (:body (<! (comm/get-pods))))]
      (println "refreshing pods")
      (swap! app-state update-in [:pods] replace pods))))


  
