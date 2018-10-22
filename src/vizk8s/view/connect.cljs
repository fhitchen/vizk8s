(ns vizk8s.view.connect
  (:require
   [clojure.string :as string]
   [cljsjs.jsplumb :as plumb]
   [vizk8s.model :as model]))

(defn hex-color [& args]
  (apply str "#" (map #(.toString % 16) args)))

(defn sleep [f ms]
  (js/setTimeout f ms))

(defn find-all
  [field value data] 
  (first(filter #(= value (field %)) data)))

(defonce instance (.getInstance js/jsPlumb (clj->js {:Container "flowchart-demo"})))

(defn nasty-hack []
  (let [instance (.getInstance js/jsPlumb (clj->js {:Container "flowchart-demo"
                                                    :Connector [ "Bezier" { :curviness 150 } ]
                                                    :Anchors  [ "BottomCenter" "TopCenter" ]}))
        rs (:replicasets @model/app-state)
        pods (:pods @model/app-state)]
    (println (type pods) (count pods))
    (doseq [{{:keys [name labels]} :metadata } rs]
      (do
        (println " name=" name)))
    (doseq [{{:keys [name labels]} :metadata } pods]
      (do
        (let [rs (subs name 0 (string/last-index-of name "-"))
	      color (hex-color (rand-int 16rFFFFFF))]
          
          (println rs " name==" name "color=" color)
          (.connect instance (clj->js {:source rs
                                       :target name
				       :paintStyle { :stroke color :strokeWidth 3}})))))))


(defn ^:export ready-fn []
  (println "READY-FN called")
  (sleep nasty-hack 2000))

(defonce ready (.ready js/jsPlumb  ready-fn))

(defn pods-panel [{:keys [pods]}]
  (into [:div {:class "flex-container"}]
        (for [{{:keys [name labels]} :metadata
               {:keys [conditions]} :status
               {:keys [selector]} :spec } pods]
          (do
            (let [rs (subs name 0 (string/last-index-of name "-"))]
              ;(println rs "->" name)
              (comment .connect js/jsPlumb (clj->js {:source rs
                                             :target name
                                             :joinStyle "round"
                                             :endpointStyle {:fillStyle "rgb(0,153,57)" :radius 7 }
                                             :connector ["Flowchart" {:cornerRadius 5}]
                                             :anchors ["Bottom" "Top"]
                                             :type "NORMAL"}))))))) 
