(ns vizk8s.view.nodes
  (:require
   [vizk8s.model :as model]))


;(:spec (nth (get-in @vizk8s.model/app-state [:nodes])3))
; (last (:conditions (:status (second (get-in @vizk8s.model/app-state [:nodes])))))

(defn find-all
  [field value data] 
  (first(filter #(= value (field %)) data)))

(defn node-panel [{:keys [nodes]}]
  (into [:div {:class "flex-container"}]
        (for [{{:keys [name]} :metadata {:keys [conditions]} :status} nodes]
          (do
            (let [status (:status (find-all :type "Ready" conditions))
                  ready (if (= status "True") "ready" "not-ready")]
              [:div {:color "blue"} name])))))



