(ns vizk8s.main
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [vizk8s.model :as model]
            [vizk8s.view.navbar :as navbar]
            [vizk8s.view.nodes :as nodes]))


(enable-console-print!)

(println "This text is printed from src/vizk8s/core.cljs. Go ahead and edit it and see reloading in action.")

(go (let [response (<! (http/get "https://api.github.com/users"
                                 {:with-credentials? false
                                  :query-params {"since" 135}}))]
      (prn (:status response))
      (prn (map :login (:body response)))))

(go (let [response (<! (http/get "/hello"))]
          (prn (:body response))))

;(go (let [response (<! (http/get "/api/v1/namespaces/com-att-cwdigital-shop-prod/pods"))]
;      (prn (:body response))))

;(go (def pods (<! (http/get "/api/v1/namespaces/com-att-cwdigital-shop-prod/pods"))))

;(println (first (:items (:body pods))))

(model/refresh-nodes model/app-state)

(defn vizk8s-main [app-state]
  [:div.content
   [:div.header
    [navbar/navbar app-state]]
   [:div.nodesbar
    [nodes/node-panel @app-state]]
   [:div.main]
   [:div.footer]])

(reagent/render-component [vizk8s-main model/app-state]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
