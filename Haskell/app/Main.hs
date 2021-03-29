module Main where

import           Control.Monad  (replicateM)
import           Criterion.Main (bench, bgroup, defaultMain, whnf)
import           Lib
import           System.Random  (getStdRandom, randomRIO)

makeIntValues :: Int -> IO [Integer]
makeIntValues num = replicateM num (randomRIO (0, 99 :: Integer))

sumValues :: [Integer] -> Integer
sumValues = foldr (+) 0

-- Alternative, recursion by hand
sumValues' :: Num a => [a] -> a
sumValues'  []      = 0
sumValues' [x]      = x
sumValues' (x : xs) = x + sumValues' xs

-- >>= mbind (monadic bind, flatmap)
-- http://www.serpentine.com/criterion/tutorial.html

main :: IO ()
main = do
    vals1000    <- makeIntValues 1000
    vals5000    <- makeIntValues 5000
    vals100000  <- makeIntValues 100000
    vals1000000 <- makeIntValues 1000000
    defaultMain [
        bgroup "Large list sum."
                [ bench "1000 integers sum"
                       (whnf sumValues vals1000)
                , bench "5000 integers sum"
                       (whnf sumValues vals5000)
                , bench "100000 integers sum"
                       (whnf sumValues vals100000)
                ]
        ]


    {-makeIntValues 1000000
    >>= \values -> print $ sumValues' values-}
