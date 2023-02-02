<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">SEA:ME Custom OS (Conv version)</h3>

  <p align="center">
    A custom embedded linux OS for SEA:ME
    <br />
    <a href="https://github.com/jun-yub-kim/SEA-ME"><strong>Explore the SEA:ME project »</strong></a>
    <br />
  </p>
</div>

<li>
      <a href="#How to use Yocto">If you are new in yocto</a>
    </li>
    <li>
      <a href="#How to add seame-conv">You know about yocto</a>
    </li>


<!-- How to add seame-conv -->
## How to add seame-conv

1. install dependencies(meta-seame) from here [meta-seame](https://github.com/chbae/meta-seame)  

2. Download meta-seameconv
   ```sh
   git clone https://github.com/jun-yub-kim/meta-seameconv.git
   ```
3. add this line in `meta-seame/classes/seame_image.bbclass`
   ```
   packagegrou-seame-devel \
   ```
4. build
   ```
   bitbake seame-image
   ```
