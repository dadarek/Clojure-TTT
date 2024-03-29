(ns clojure_ttt.minimax-spec
  (:use clojure_ttt.minimax)
  (:use [speclj.core]))

(describe "Minimax"
  (context "Scoring"
    (it "Knows all winning combinations"
      (should (won? #{1 2 3}))
      (should (won? #{4 5 6}))
      (should (won? #{7 8 9}))
      (should (won? #{1 4 7}))
      (should (won? #{2 5 8}))
      (should (won? #{3 6 9}))
      (should (won? #{1 5 9}))
      (should (won? #{3 5 7})))

    (it "Knows some losing combinations"
      (should-not (won? #{2 3}))
      (should-not (won? #{1 5 6}))
      (should-not (won? #{7 8}))
      (should-not (won? #{9}))
      (should-not (won? #{3 7})))

    (it "scores winning square as 1"
      (should= 1 (score :x 9 '(:x :o :x
                               :o :x :o
                               :o :x nil))))

    (it "scores tie square as 0"
      (should= 0 (score :x 2 '(:o  nil :o
                               :x  :o  :x
                               :x  :o  :x))))

    (it "scores a losing, second-to-last move as -1"
      (should= -1 (score :x 6 '(:o  :x  :o
                               :x  :o  nil
                               :x  :o  nil))))

    (it "scores a guaranteed-tie second-to-last move as 0"
      (should= 0 (score :x 9 '(:o  :x  :o
                               :x  :o  nil
                               :x  :o  nil))))

    (it "scores a possible-win third-to-last move as 0"
      (should= 0 (score :x 6 '(:x  :o  nil
                               :o  :o  nil
                               :x  :x  nil))))

    (it "scores a guaranteed-loss third-to-last move as -1"
      (should= -1 (score :x 6 '(:o  :x  :o
                                :x  :o  nil
                                :x  nil nil))))

    (it "scores a one-possible-win third-to-last move as 0"
      (should= 0 (score :x 9 '(:o  :x  :o
                               :x  :o  nil
                               :x  nil nil))))

    (it "scores a one-possible-win one-guaranteed-loss third-to-last move as -1"
      (should= -1 (score :x 8 '(:o  :x  :o
                                :x  :o  nil
                                :x  nil nil)))))

  (context "Next Move"
    (it "makes the correct first move"
      (should= 1 (next-move :x '(nil nil nil
                                 nil nil nil
                                 nil nil nil))))

    (it "selects the correct blocking next move"
      (should= 9 (next-move :x '(:o  :x  :o
                                 :x  :o  nil
                                 :x  nil nil))))

    (it "makes the correct second move"
      (should= 5 (next-move :o '(:x  nil nil
                                 nil nil nil
                                 nil nil nil))))

    (it "selects the correct winning next move"
      (should= 3 (next-move :x '(:x  :x  nil
                                 :o  :o  nil
                                 nil nil nil))))))

(run-specs)

