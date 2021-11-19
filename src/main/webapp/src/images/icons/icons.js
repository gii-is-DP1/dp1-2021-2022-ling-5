import bell from './bell.png';
import forum from './forum.png';
import share from './share.png';

const ls = [bell, forum, share];

export default function iconImg(id) {
  return ls[id];
}
