(ns vizk8s.communication
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defn get-nodes []
  (go (let [nodes (<! (http/get "/api/v1/nodes"))]
        nodes)))

(defn get-deployments []
  (go (let [deployments (<! (http/get "/apis/extensions/v1beta1/namespaces/com-att-cwdigital-shop-prod/deployments"))]
        deployments)))

(defn get-replicasets []
  (go (let [replicasets (<! (http/get "/apis/extensions/v1beta1/namespaces/com-att-cwdigital-shop-prod/replicasets"))]
        replicasets)))

(defn get-pods []
  (go (let [pods (<! (http/get "/api/v1/namespaces/com-att-cwdigital-shop-prod/pods"))]
        pods)))
