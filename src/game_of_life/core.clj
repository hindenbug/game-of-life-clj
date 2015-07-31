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

(defn setup-w [w h]
  (vec (repeat w (vec (repeat h "-")))))

(defn neighbours [[x y]]
  (vec (for [i (range -1 2)
             j (range -1 2)
             :when (not= 0 i j)]
         [(+ x i) (+ y j)])))

(defn all-cells [world]
  (vec (for [[x row] (map-indexed vector world)
             [y value] (map-indexed vector row)]
         [x y])))

(defn is_alive? [world cell]
  (= "x" (get-in world cell)))

(defn alive-neighbours [world cell]
  (filter #(is_alive? world %)
          (neighbours cell)))

(defn survive? [world cell]
  (let [n (count (alive-neighbours world cell))]
    (or (= n 3)
        (and (= n 2) (is_alive? world cell)))))

(defn tick [world]
  (let [cells (all-cells world)
        to_live (vec (filter #(survive? world %) cells))]
    ))

;; alive as set of cordinates
(defn setup-world [w h cells]
  (reduce (fn [world loc]
            (assoc-in world loc "x"))
          (setup-w w h) cells))

