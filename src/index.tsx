import React, {useState} from "react";
import {render} from "react-dom";
import {GiphyFetch} from "@giphy/js-fetch-api";
import {IGif} from "@giphy/js-types";
import {Gif} from "@giphy/react-components";
import {useAsync} from "react-async-hook";

const giphyFetch = new GiphyFetch("sXpGFDGZs0Dv1mmNFvYaGUvYwKX0PWIh");

function GifDemo() {
  const [gif, setGif] = useState<IGif | null>(null);
  useAsync(async () => {
    const {data} = await giphyFetch.random({tag: 'Not today'});
    setGif(data);
  }, []);
  return gif && <Gif gif={gif} width={400}/>;
}

function App() {
  return (
      <>
        <h4>Not today!</h4>
        <GifDemo/>
      </>
  );
}

const rootElement = document.getElementById("root");
render(<App/>, rootElement);
