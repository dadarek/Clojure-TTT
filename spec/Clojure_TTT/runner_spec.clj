(ns clojure_ttt.runner-spec
  (:use [clojure_ttt.runner])
  (:use [clojure_ttt.board_utilities])
  (:use [speclj.core]))

(defrecord MockPlayer [moves symbol-ref next-move-pointer]
  Player
  (next-move [this x-or-o board]
    (do
      (dosync (ref-set symbol-ref x-or-o))
      (let [result (nth moves @next-move-pointer)]
        (dosync (ref-set next-move-pointer (inc @next-move-pointer)))
       result))))

(defrecord MockUI [counter]
  RunnerUI
  (redraw [this board] (dosync (ref-set counter (inc @counter))))
)

(describe "Game Runner"
  (it "Listens to players"
    (should= '(:x :o :x
               :x :o :o
               :o :x :x)
              (run (MockPlayer. '(1 3 4 8 9) (ref nil) (ref 0))
                   (MockPlayer. '(2 5 6 7) (ref nil) (ref 0))
                   (MockUI. (ref 0)))))

  (it "Stops when somebody wins"
    (should= '(:x  :x  :x
               :o  :o  nil
               nil nil nil)
              (run (MockPlayer. '(1 2 3) (ref nil) (ref 0))
                   (MockPlayer. '(4 5) (ref nil) (ref 0))
                   (MockUI. (ref 0)))))

  (it "Doesn't allow duplicate moves"
    (should= '(:x  :x  :x
               :o  :o  nil
               nil nil nil)
              (run (MockPlayer. '(1 3 2) (ref nil) (ref 0))
                   (MockPlayer. '(1 4 5) (ref nil) (ref 0))
                   (MockUI. (ref 0)))))

  (it "Notifies UI on each turn."
    (let [counter (ref 0)]
      (run (MockPlayer. '(1 3 2) (ref nil) (ref 0))
           (MockPlayer. '(1 4 5) (ref nil) (ref 0))
           (MockUI. counter))
      (should= 6 @counter)))

  (it "Tells first player he's 'x', second player he's 'o'"
    (let [x-ref (ref nil)
          o-ref (ref nil)]
      (run (MockPlayer. '(1 2 3) x-ref (ref 0))
           (MockPlayer. '(4 5) o-ref (ref 0))
           (MockUI. (ref 0)))
      (should= :x @x-ref)
      (should= :o @o-ref))))

(run-specs)
