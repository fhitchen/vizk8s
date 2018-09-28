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

       
  
