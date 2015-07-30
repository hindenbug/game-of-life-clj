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
  (vec (repeat w (vec (repeat h nil)))))

(defn neighbours [[x y]]
  (vec (for [i (range -1 2)
             j (range -1 2)
             :when (not= 0 i j)]
         [(+ x i) (+ y j)])))

(defn all-alive-cells [world]
  (vec (for [[x row] (map-indexed vector world)
             [y value] (map-indexed vector row)]
         [x y])))

(defn alive-neighbours [world cell]
  (count (filter #(= "x" (get-in world %))
                 (neighbours cell))))

(defn survive? [world cell]
  (let [alive-nbrs (alive-neighbours world cell)]
    (and (nil? (get-in world cell)) (= 3 alive-nbrs))))
      ;;(or (= alive-nbrs 2) (= alive-nbrs 3))))

(defn tick [world]
  (reduce (fn [world loc]
            (if (survive? world loc)
              (assoc-in world loc "x")
              (assoc-in world loc nil)))
          world (all-alive-cells world)))

;; alive as set of cordinates
(defn setup-world [w h cells]
  (reduce (fn [world loc]
            (assoc-in world loc "x"))
          (setup-w w h) cells))

