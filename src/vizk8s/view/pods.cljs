(ns vizk8s.view.pods
  (:require
   [clojure.string :as string]
   [cljsjs.jsplumb :as plumb]
   [vizk8s.model :as model]))

(defn find-all
  [field value data] 
  (first(filter #(= value (field %)) data)))

(defn pods-panel [{:keys [pods]}]
  (into [:div {:class "flex-container"}]
        (for [{{:keys [name labels]} :metadata
               {:keys [conditions]} :status
               {:keys [selector]} :spec } pods]
          (do
            (let [app (:app labels)]
              [(keyword (str "div#" name)) name])))))
