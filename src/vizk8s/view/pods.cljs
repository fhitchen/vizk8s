(ns vizk8s.view.pods
  (:require
   [cljsjs.jsplumb :as plumb]
   [vizk8s.model :as model]))

(defn find-all
  [field value data] 
  (first(filter #(= value (field %)) data)))

(defonce instance (.getInstance js/jsPlumb (clj->js {:Container "pods"})))

(defn ^:export ready-fn []
  (println "READY-FN called")
  (.importDefaults instance (clj->js {:Connector [ "Bezier" { :curviness 150 } ]
                                      :Anchors  [ "TopCenter" "BottomCenter" ]})))

(defonce ready (.ready js/jsPlumb ready-fn))

(defn pods-panel [{:keys [pods]}]
  (into [:div {:class "flex-container"}]
        (for [{{:keys [name labels]} :metadata
               {:keys [conditions]} :status
               {:keys [selector]} :spec } pods]
          (do
            (.connect js/jsPlumb (clj->js {:source name
                                           :target "FOO"
                                           :joinStyle "round"
                                           :endpoint "BLANK"
                                           :connector ["flowchart" {:cornerRadius 5}]
                                           :scope "somescope"}))
            ;(.fire js/jsPlumb "jsPlumbDemoLoaded" instance) 
            (println name)
            (let [app (:app labels)]
              [:div name])))))
