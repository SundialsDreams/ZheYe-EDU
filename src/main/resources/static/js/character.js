function frame(t) {
  
  const colors = [
    "#f77194",
    "#ebf771",    
    "#90f771",
    "#71f7eb",
    "#7187f7",
    "#f7187f",
    "#e071f7"
  ];
  const m = 0.005;
  context.clearRect(0,0,canvas.width,canvas.height);
  
  context.save();
  context.translate(canvas.width >> 1, canvas.height >> 1);
  context.font = "72px monospace";
  context.textAlign = "center";
  context.textBaseline = "middle";
  const { width } = context.measureText(text);
  for (let i = 0; i < text.length; i++) {
    const character = text.charAt(i);
    const { width: x } = context.measureText(text.substr(0,i));
    const { width: cw } = context.measureText(character);
    const p = t + (i * multiplier);
    const y = Math.sin(p * m) * amplitude;
    context.save();
    context.translate(x - (width >> 1) + (cw >> 1),y);
    context.rotate(Math.cos(p * m) * 0.5);
    //context.fillText(character, -x, 0); // I LOVE THIS
    context.fillStyle = colors[i % colors.length];
    context.fillText(character, 0, 0); // I LOVE THIS
    context.restore();
  }
  
  context.beginPath();
  
  for (let i = 0; i < text.length; i++) {
    const p = t + (i * multiplier);
    const pn = t + ((i + 1) * multiplier);
    const y = Math.sin(p * m) * amplitude;
    const yn = Math.sin(pn * m) * amplitude;
    const character = text.charAt(i);
    const { width: x } = context.measureText(text.substr(0,i));
    const { width: cw } = context.measureText(character);
    if (i === 0) {
      context.moveTo(
        x - (width >> 1) + (cw >> 1),
        y + 64
      );
    } else {
      context.quadraticCurveTo(
        x - (width >> 1) + (cw >> 1),
        y + 64,
        x - (width >> 1) + cw,
        y + ((yn - y) * 0.5) + 64
      );
    }
  }
  context.lineWidth = 5;
  context.strokeStyle = "#71f7eb";
  context.stroke();
  
  context.beginPath();
  for (let i = 0; i < text.length; i++) {
    const p = t + (i * multiplier);
    const pn = t + ((i + 1) * multiplier);
    const y = Math.sin(p * m) * amplitude;
    const yn = Math.sin(pn * m) * amplitude;
    const character = text.charAt(i);
    const { width: x } = context.measureText(text.substr(0,i));
    const { width: cw } = context.measureText(character);
    if (i === 0) {
      context.moveTo(
        x - (width >> 1) + (cw >> 1),
        y + 80
      );
    } else {
      context.quadraticCurveTo(
        x - (width >> 1) + (cw >> 1),
        y + 80,
        x - (width >> 1) + cw,
        y + ((yn - y) * 0.5) + 80
      );
    }
  }
  context.lineWidth = 5;
  context.strokeStyle = "#71f7eb";
  context.stroke();
  
  context.beginPath();
  for (let i = 0; i < text.length; i++) {
    const p = t + (i * multiplier);
    const pn = t + ((i + 1) * multiplier);
    const y = Math.sin(p * m) * amplitude;
    const yn = Math.sin(pn * m) * amplitude;
    const character = text.charAt(i);
    const { width: x } = context.measureText(text.substr(0,i));
    const { width: cw } = context.measureText(character);
    if (i === 0) {
      context.moveTo(
        x - (width >> 1) + (cw >> 1),
        y + 96
      );
    } else {
      context.quadraticCurveTo(
        x - (width >> 1) + (cw >> 1),
        y + 96,
        x - (width >> 1) + cw,
        y + ((yn - y) * 0.5) + 96
      );
    }
  }
  context.lineWidth = 5;
  context.strokeStyle = "#71f7eb";
  context.stroke();
  
  context.restore();
  
  requestAnimationFrame(frame);
}

function resize() {
  canvas.width = canvas.offsetWidth;
  canvas.height = canvas.offsetHeight;
}

function start() {
  addEventListener("resize", resize);
  dispatchEvent(new Event("resize"));
  requestAnimationFrame(frame);  
}

start();