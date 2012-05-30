(ns clojure_ttt.generic_minimax-spec
  (:use [clojure_ttt.generic_minimax])
  (:use [speclj.core]))

(describe "Generic Minimax"
  (it "Returns 0 on moves that tie"
    (let [wins (fn [move context] (not= 0 move))
          ties (fn [move context] (= 0 move))]
      (should= '(0 1 1)
               (score #{0 1 2} nil wins ties nil))))

  (it "Returns 1 for moves that win"
    (let [wins (fn [move context] (= 1 move))
          ties (fn [move context] (not= 1 move))]
      (should= '(0 1 0)
               (score #{0 1 2} nil wins ties nil))))

  (it "Returns negated scores of child moves"
    (let [wins (fn [move context] (= :first-context context))
          ties (fn [move context] false)
          next-context (fn [move context] :first-context)]
      (should= '(-1 -1)
               (score #{0 1} nil wins ties next-context))))

  (it "Returns worst case child moves"
    (let [wins (fn [move context]
                 (and
                   (= :first-context context)
                   (= move 0)))
          ties (fn [move context]
                 (and
                   (= :first-context context )
                   (= move 1)))
          next-context (fn [move context] :first-context)]
      (should= '(0 -1)
               (score #{0 1} nil wins ties next-context)))))

(run-specs)
