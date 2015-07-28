(ns game-of-life.core
  (:gen-class))

(def north [0 1])
(def south [0 -1])
(def north-east [1 1])
(def north-west [-1 1])
(def south-east [1 -1])
(def south-west [-1 -1])
(def east [1 0])
(def west [-1 0])

(defn setup-world [w h & alive]
  (vec (for [y (range w)]
         (vec (for [x (range h)]
                (if (contains? (first alive) [y x])
                  "x"
                  " "))))))

(defn alive)

(defn neighbours [[x y]]
  (set (for [i (range -1 2)
             j (range -1 2)
             :when (not (and (= x i) (= y j)))]
         [i j])))

