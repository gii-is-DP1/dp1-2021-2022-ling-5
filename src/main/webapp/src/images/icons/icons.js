import bell from './bell.png';
import forum from './forum.png';
import share from './share.png';
import logo from './logoDobble.png';

const ls = [bell, forum, share, logo];

export default function iconImg(id) {
  return ls[id];
}
