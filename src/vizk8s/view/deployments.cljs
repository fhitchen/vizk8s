(ns vizk8s.view.deployments
  (:require
   [vizk8s.model :as model]))

(defn find-all
  [field value data] 
  (first(filter #(= value (field %)) data)))

(defn deployment-panel [{:keys [deployments]}]
  (into [:div {:class "flex-container"}]
        (for [{{:keys [name labels]} :metadata
               {:keys [conditions]} :status
               {:keys [selector]} :spec } deployments]
          (do
            (println name)
            (let [app (:app labels)]
              [:div app])))))
