(ns vizk8s.view.connect
  (:require
   [clojure.string :as string]
   [cljsjs.jsplumb :as plumb]
   [vizk8s.model :as model]))


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
        pods (:pods @model/app-state)]
    (println (type pods) (count pods))
    (doseq [{{:keys [name labels]} :metadata } pods]
      (do
        (let [rs (subs name 0 (string/last-index-of name "-"))]
          
          (println rs " name=" name)
          (.connect instance (clj->js {:source rs
                                       :target name })))))))



    (comment .connect instance (clj->js {:source "commercemobileproductvalidation-1-d2a-77cc7f5565"
                                 :target "commercemobileproductvalidation-1-d2a-77cc7f5565-rb6jj" }))
    (comment .connect instance (clj->js {:source "commercemobileproductvalidation-1-d2a-77cc7f5565"
                                 :target "commercemobileproductvalidation-1-d2a-77cc7f5565-xtvx2" }))
                                                   
  (comment .importDefaults instance (clj->js {:Connector [ "Bezier" { :curviness 150 } ]
                                      :Anchors  [ "TopCenter" "BottomCenter" ]
                                      :DragOptions {:cursor "pointer" :zIndex 2000 }}))

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
