(ns vizk8s.communication
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defn get-nodes []
  (go (let [nodes (<! (http/get "/api/v1/nodes"))]
        nodes)))

(println get-nodes)
