(ns game-of-life.core
  (:gen-class))

(defn all-live-cells [world]
  (set (for [[x row] (map-indexed vector world)
             [y value] (map-indexed vector row)
             :when (= "x" value)]
         [x y])))

(defn neighbours [[x y]]
  (set (filter (fn [[x y]] (and (>= x 0) (>= y 0)))
       (for [i (range -1 2)
             j (range -1 2)
             :when (not= 0 i j)]
         [(+ x i) (+ y j)]))))

(defn tick [cells]
  (set (for [[cell n] (frequencies (mapcat neighbours cells))
             :when (if (contains? cells cell) (or (= n 3) (= n 2)) (= n 3))]
         cell)))

;; alive as set of cordinates
(defn setup-world [w h cells]
  (set (vec (for [x (range w)]
         (vec (for [y (range h)]
                (if (contains? cells [x y]) "x" "-")))))))

(defn run [w h n]
  (def world (setup-world w h #{[2 0] [2 1] [2 2] [1 2] [0 1]}))
  (dotimes [i 4]
    (println)
    (first (map println (setup-world w h (first (drop i (iterate tick (all-live-cells world)))))))))

