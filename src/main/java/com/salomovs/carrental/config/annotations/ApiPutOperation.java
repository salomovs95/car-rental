package com.salomovs.carrental.config.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static  java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;

import org.springframework.core.annotation.AliasFor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target(METHOD)
@Retention(RUNTIME)
@Operation( summary="" )
@ApiResponses({
  @ApiResponse(
    responseCode="200",
    content=@Content(
      mediaType="application/json",
      contentSchema=@Schema( implementation=Map.class )
    )
  ),
  @ApiResponse(
    responseCode="404",
    content=@Content(
      mediaType="application/json",
      contentSchema=@Schema( implementation=Map.class )
    )
  ),
  @ApiResponse(
    responseCode="400",
    content=@Content(
      mediaType="application/json",
      contentSchema=@Schema( implementation=Map.class )
    )
  ),
  @ApiResponse(
    responseCode="500",
    content=@Content(
      mediaType="application/json",
      contentSchema=@Schema( implementation=Map.class )
    )
  )
})
public @interface ApiPutOperation {
  @AliasFor(annotation=Operation.class, attribute="summary")
  String summary();
}
