#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>

/* Para colocar o caminho usando pontos em vermelho: mudar o programa
 * para gerar comandos
 *
 * <circle cx="31.5" cy="0.5" r="0.2" stroke="red" fill="red" />
 * <circle cx="32.5" cy="0.5" r="0.2" stroke="red" fill="red" />
 * <circle cx="33.5" cy="0.5" r="0.2" stroke="red" fill="red" />
 *
 */

void svgline( int x1, int y1, int x2, int y2 , FILE *arq) {
    fprintf(arq, " <polyline points=\"%d,%d %d,%d\"/>\n", x1, y1, x2, y2);
}

char **split(char frase[], char separador)
{
    int i, j, k, contsep = 0;

     for(i=0,contsep=0;i<strlen(frase);i++)
       if(frase[i] == separador)
          contsep++;

    char  aux[contsep][20];
    char **result = (char**)malloc(contsep*sizeof(char));

    if(contsep)
    {
        for(i=0; i<=contsep; i++ )
          *(result + i) = (char*)malloc(40*sizeof(char));

        for(i=0,k=0,j=0; i < strlen(frase); i++)
           if(frase[i] != separador)
           {
              aux[k][j] = frase[i];
              j++;
           }
           else
           {
              aux[k][j] = 0;
              k++;
              j=0;
           }
        aux[k][j] = 0;

        for(i=0;i<=contsep;i++)
          *(result+i) = strcpy(*(result+i), aux[i]);

        return result;
    }
    else
        printf("Nenhum Separador Encontrado");
}

void process ( int w, int h , FILE *arqMaze, FILE *arqOut, FILE *arqPath) {

  int N = 8;
  int L = 4;
  int S = 2;
  int O = 1;

  int l = 0;
  int c = 0;

  char t;
  int x,y,aux;
  aux = 0;
  while ((t = getc(arqMaze) ) != EOF ) {

    if(isspace(t)){

    }else{
    switch ( t ) {
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9': t -= '0'; break;
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'f': t = t - 'a' + 10; break;
        default: continue;
    }

    if ( ( t & N ) == N ) svgline( c, l , c + 1, l, arqOut);
    if ( ( t & S ) == S ) svgline( c, l + 1, c + 1, l + 1, arqOut);
    if ( ( t & O ) == O ) svgline( c , l , c , l + 1, arqOut);
    if ( ( t & L ) == L ) svgline( c + 1, l , c + 1, l + 1, arqOut);

    c++;
    if ( c == w ) {
        c = 0;
        l++;
    }
    }

  }
    char **m;
    char s[100];
    char *result;

  /*fgets(s, 100, arqPath);
  char **m = split(s, ' ');
  x = atoi(m[0]);
  y = atoi(m[1]);
*/
  int i = 1;
  while (!feof(arqPath))
  {
    fgets(s, 100, arqPath);  // o 'fgets' lê até 99 caracteres ou até o '\n'
    m = split(s, ' ');
    x = atoi(m[1]);
    y = atoi(m[0]);
            fprintf(arqOut, "\n<circle cx=\"%d.5\" cy=\"%d.5\" r=\"0.2\" stroke=\"red\" fill=\"red\" />", x,y);
            printf("\n<circle cx=\"%d.5\" cy=\"%d.5\" r=\"0.2\" stroke=\"red\" fill=\"red\" />", x,y);
    }
  fclose(arqPath);
  fclose(arqMaze);
}

int main ( ) {

  int w, h;

  FILE *arqMaze, *arqPath, *arqOut;
  char a;
  char s[100];
  char *result;
  int i;

  arqMaze = fopen("caso250a.txt", "rt");
  arqOut = fopen("caso250a1.svg", "w");
  arqPath = fopen("caso250aPath.txt", "rt");

  if(arqMaze == NULL){
    printf("Erro na abertura do arquivo");
  }

  if(arqOut == NULL){
    printf("Erro na abertura do arquivo");
  }

  if(arqPath == NULL){
    printf("Erro na abertura do arquivo");
  }

  fgets(s, 100, arqMaze);
  char **mat = split(s, ' ');
  w = atoi(mat[0]);
  h = atoi(mat[1]);
  //fscanf( stdin, "%d %d", &h, &w );

  fputs ( "<?xml version=\"1.0\" standalone=\"no\"?>", arqOut );
  fprintf (arqOut, "\n<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%dcm\" height=\"%dcm\" viewBox=\"-0.1 -0.1 %f %f\">", w, h, w+0.2, h+0.2 );
  fputs ( "\n<g style=\"stroke-width:.1; stroke:black; stroke-linejoin:miter; stroke-linecap:butt; \">", arqOut);

  process( w, h, arqMaze, arqOut, arqPath);

  // Ex: gerando circulos no labirinto
  puts ("<circle cx=\"11.5\" cy=\"0.5\" r=\"0.2\" stroke=\"red\" fill=\"red\" />");
  puts ("<circle cx=\"12.5\" cy=\"0.5\" r=\"0.2\" stroke=\"red\" fill=\"red\" />");
  puts ("<circle cx=\"13.5\" cy=\"0.5\" r=\"0.2\" stroke=\"red\" fill=\"red\" />");

  // Finaliza
  fputs ( "</g>\n" ,arqOut);
  fputs ( "</svg>\n" ,arqOut);
  return 0;
}
